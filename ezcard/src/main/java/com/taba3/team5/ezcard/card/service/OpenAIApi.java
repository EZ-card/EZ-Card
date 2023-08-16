package com.taba3.team5.ezcard.card.service;

import com.taba3.team5.ezcard.card.dto.CardDTO;
import com.taba3.team5.ezcard.card.service.CardService;
import com.taba3.team5.ezcard.ezbot.service.CardInfo;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class OpenAIApi {
    private static final String API_KEY = "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";

    private final CardService cardService;
    public OpenAIApi(CardService cardService) {
        this.cardService = cardService;
        // 생성자에서 cardService를 사용하는 초기화 작업을 진행할 수 있음
    }

    private String extractCardName(String gptResponse) {
        String cardName = "";
        String cardNamePrefix = "카드이름 :";
        int startIndex = gptResponse.indexOf(cardNamePrefix);

        if (startIndex != -1) {
            int endIndex = gptResponse.indexOf("/", startIndex);
            if (endIndex != -1) {
                cardName = gptResponse.substring(startIndex + cardNamePrefix.length(), endIndex).trim();
            }
        }

        return cardName;
    }


    private String performCardRecommendation(String gptResponse) {
//        String cardName = extractCardName(gptResponse);
        String cardName = "KB국민 My WE:SH 카드";
        // CardService를 사용하여 카드 정보 가져오기
        CardDTO cardDTO = cardService.getCardInfoByCardName(cardName);

        if (cardDTO != null) {
            // 카드 정보를 사용하여 채팅 메시지 생성
            String cardInfoMessage = "카드 이름: " + cardDTO.getCardName() + "\n"
                    + "은행: " + cardDTO.getCardBank() + "\n"
                    + "멤버십: " + cardDTO.getCardMembership() + "\n"
                    + "레코드: " + cardDTO.getCardRecord() + "\n"
                    + "요약: " + cardDTO.getCardSummary() + "\n"
                    + "이미지: " + cardDTO.getCardImage() + "\n"
                    + "카드 유형: " + cardDTO.getCardType();
            return cardInfoMessage;
        } else {
            return "해당하는 카드 정보를 찾을 수 없습니다.";
        }
    }

    public String ezbot(String prompt) {
        String responseBody = "";
        String cardInfo = "";

        // 사용자 입력에서 "카드추천해줘" 문장이 있는지 확인합니다.
        boolean containsRecommendationRequest = prompt.contains("카드추천해줘");

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("messages", new JSONArray()
                .put(new JSONObject()
                        .put("role", "system")
                        .put("content", CardInfo.MESSAGE +
                                "너는 지금 주어진 카드 중 카드를 1개 추천해주는 상담사야. " +
                                "형식은 '카드이름 : /카드추천 이유 : '으로 해줘. 추천 이유는 1줄로 짧게 해줘. " +
                                "카드추천해줘라고 말했을 때만 카드 추천하고 아닐 때는 그냥 대화해."))
                .put(new JSONObject()
                        .put("role", "user")
                        .put("content", prompt)));
        jsonBody.put("max_tokens", 2000);
        jsonBody.put("temperature", 0.0);
        jsonBody.put("model", "gpt-3.5-turbo");

        try {
            // HttpClient 인스턴스 생성
            HttpClient client = HttpClient.newHttpClient();

            // HttpRequest 빌드
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody.toString()))
                    .build();

            // 요청 보내고 응답 받아옴
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 응답 본문 분석하여 처리
            responseBody = extractAnswer(response.body());

            if (containsRecommendationRequest) {
                // "카드추천해줘" 문장이 있는 경우, 카드 추천 작업 수행
                System.out.println("카드 추천 작업 수행");
                cardInfo = performCardRecommendation(responseBody);
            } else {
                // "카드추천해줘" 문장이 없는 경우, 일반 대화 작업 수행
                System.out.println("일반 대화 작업 수행");
                cardInfo = responseBody;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cardInfo;
    }

    private String extractAnswer(String responseJson) {
        JSONObject jsonObject = new JSONObject(responseJson);

        if (jsonObject.has("choices")) {
            JSONArray choicesArray = jsonObject.getJSONArray("choices");
            JSONObject messageObject = choicesArray.getJSONObject(0).getJSONObject("message");
            return messageObject.getString("content").trim();
        } else {
            System.err.println("API 응답에서 오류 발생: " + responseJson);
            return "API 호출 중 오류가 발생했습니다.";
        }
    }
}
