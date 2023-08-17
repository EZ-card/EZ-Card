package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.CardInfo;
import com.taba3.team5.ezcard.dto.CardDTO;
import com.taba3.team5.ezcard.service.CardService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class OpenAIApi {
    @Value("${key.gpt}")
    private String API_KEY ;

    private final CardService cardService;
    public OpenAIApi(CardService cardService) {
        this.cardService = cardService;
        // 생성자에서 cardService를 사용하는 초기화 작업을 진행할 수 있음
    }

    private List<String> extractCardNames(String gptResponse) {
        List<String> cardNames = new ArrayList<>();
        String cardNamePrefix = "카드이름 :";
        int startIndex = gptResponse.indexOf(cardNamePrefix);

        while (startIndex != -1) {
            int endIndex = gptResponse.indexOf(" / ", startIndex);

            if (endIndex != -1) {
                String cardName = gptResponse.substring(startIndex + cardNamePrefix.length(), endIndex).trim();
                cardNames.add(cardName);
                startIndex = gptResponse.indexOf(cardNamePrefix, endIndex);
            } else {
                break;
            }
        }

        return cardNames;
    }

    public String ezbot(String prompt) {
        List<String> responseList = new ArrayList<>();
        String responseBody = "";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("messages", new JSONArray()
                .put(new JSONObject()
                        .put("role", "system")
                        .put("content", CardInfo.MESSAGE +
                                "너는 지금 주어진 카드 중 카드를 2개 추천해주는 상담사야. " +
                                "형식은 '카드이름 : /카드추천 이유 : '으로 해줘. 추천 이유는 1줄로 짧게 해줘. " +
                                "카드와 추천이 함께 들어간 문장일때만 카드 추천하고 아닐 때는 그냥 대화해."))
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
            responseList.add(responseBody);

            // "카드추천해줘" 문장이 있는 경우, 카드 추천 작업 수행
            if (prompt.contains("카드")&&prompt.contains("추천")) {
                List<String> cardNames = extractCardNames(responseBody); //카드이름이 담겨있는 리스트 받아오기
                StringBuilder cardInfoMessage = new StringBuilder(); //카드 정보를 담을

                for (String cardName : cardNames) {
                    CardDTO cardDTO = cardService.getCardInfoByCardName(cardName);
                    System.out.println(cardName);

                    if (cardDTO != null) {
                        // 카드 정보를 사용하여 채팅 메시지 생성
                        cardInfoMessage.append("카드 이름: ").append(cardDTO.getCardName()).append("\n")
                                .append("은행: ").append(cardDTO.getCardBank()).append("\n")
                                .append("멤버십: ").append(cardDTO.getCardMembership()).append("\n")
                                .append("레코드: ").append(cardDTO.getCardRecord()).append("\n")
                                .append("요약: ").append(cardDTO.getCardSummary()).append("\n")
                                .append("이미지: ").append(cardDTO.getCardImage()).append("\n")
                                .append("카드 유형: ").append(cardDTO.getCardType()).append("\n\n");
                        String cardInfo = cardInfoMessage.toString();
                        responseList.add(cardInfo);

                        cardInfoMessage = new StringBuilder();
                    }
                }
            }
            return responseBody;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
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
