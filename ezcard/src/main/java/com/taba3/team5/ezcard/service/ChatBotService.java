package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.chatbot.ChatBotResponse;
import com.taba3.team5.ezcard.dto.card.CardDto;
import com.taba3.team5.ezcard.model.GptApiClient;
import com.taba3.team5.ezcard.model.GptModel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class ChatBotService {
    private final CardService cardService;
    private final GptApiClient gptApiClient;

    @Autowired
    public ChatBotService(CardService cardService, GptApiClient gptApiClient) {
        this.cardService = cardService;
        this.gptApiClient = gptApiClient;
    }

    public ChatBotResponse ezbot(String userinput) {
        ChatBotResponse chatBotResponse = new ChatBotResponse();

        JSONObject jsonBody = GptModel.createJsonBody(userinput);

        try {
            String responseFrompt = gptApiClient.getGptResponse(jsonBody);
            String gptResponse = extractAnswer(responseFrompt);

            // "카드추천해줘" 문장이 있는 경우, 카드 추천 작업 수행
            if (userinput.contains("카드") && userinput.contains("추천")) {
                String cardName = extractCardNames(gptResponse); //카드이름 받아오기

                CardDto cardDto = CardService.findCardByName(cardName); //카드 정보를 이름으로 검색하여 저장

                if (cardDto != null) {
                    // 카드 정보를 사용하여 응답 생성
                    chatBotResponse.setGptResponse(gptResponse); //응답 컨테너에 gpt답변 저장
                    chatBotResponse.setCardDto(cardDto); //응답 컨터이너에 카드정보 저장
                } else {
                    // 카드를 찾지 못한 경우 예외 처리
                    chatBotResponse.setGptResponse(gptResponse);
                }
            } else {
                // 카드 추천이 아닌 다른 응답 생성
                chatBotResponse.setGptResponse(gptResponse);
            }

            return chatBotResponse;

        } catch (Exception e) {
            e.printStackTrace();
            chatBotResponse.setGptResponse("오류가 발생했습니다.");
        }

        return chatBotResponse;
    }

    //gpt답변에서 카드이름 추출
    private String extractCardNames(String gptResponse) {
        // 정규 표현식 패턴 설정
        String pattern = "카드이름:\\s*(.*?)(?:\\s*,|\\s*/|$)";

        Pattern r = Pattern.compile(pattern);

        Matcher matcher = r.matcher(gptResponse);
        if (matcher.find()) {
            String cardName = matcher.group(1);
            return cardName;
        } else {
            return "카드 이름을 추출할 수 없습니다.";
        }
    }


    //gpt와 통신 후 결과에서 gpt답변만 추출
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
