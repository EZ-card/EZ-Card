package com.taba3.team5.ezcard.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//gpt의 시스템 role 설정과 채팅 모델의 설정을 관리하는 서비스
public class GptModel {

    public static JSONObject createJsonBody(String userinput, String previousAssistantResponse) {
        JSONArray messages = new JSONArray();

        // 이전 Assistant 응답을 history로 추가
        if (previousAssistantResponse != null && !previousAssistantResponse.isEmpty()) {
            JSONObject assistantMessage = new JSONObject()
                    .put("role", "assistant")
                    .put("content", previousAssistantResponse);
            messages.put(assistantMessage);
        }

        JSONObject cardInfoMessage = new JSONObject()
                .put("role", "assistant")
                .put("content", CardInfo.MESSAGE);
        messages.put(cardInfoMessage);

        // system 메시지 추가
        JSONObject systemMessage = new JSONObject()
                .put("role", "system")
                .put("content",
                        "When printing a sentence with a card name, print it out in this form, \"카드이름: /추천이유:\"." +
                        "If you try to chat in areas other than card recommendations, you'll say, \"저는 ezbot이에요! 여러분의 이야기를 들려주시면 카드를 추천해 드릴게요.\""+
                        "If the user asks for the reason for the recommendation, please refer to \"assistant role\" and explain the reason.");
        messages.put(systemMessage);

        // 사용자 입력 추가
        JSONObject userMessage = new JSONObject()
                .put("role", "user")
                .put("content", userinput);
        messages.put(userMessage);

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("messages", messages);
        jsonBody.put("max_tokens", 2000);
        jsonBody.put("temperature", 0.0);
        jsonBody.put("model", "gpt-3.5-turbo");
        return jsonBody;
    }
}