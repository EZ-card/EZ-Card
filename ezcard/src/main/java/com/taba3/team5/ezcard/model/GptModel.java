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
                .put("content", "너는 카드를 사용자에게 가장 알맞는 혜택을 가지고 있는 카드를 1개 추천해주는 상담사야." +
                        "If you try to chat in areas other than card recommendations, you'll say, \"안녕하세요. 저는 Ez:bot이에요! 여러분의 이야기를 들려주시면 카드를 추천해 드릴게요.\""+
                        "If the user asks for the reason for the recommendation, assistant role의 카드정보만을 이용하여 사용자에게 가장 적합한 카드를 추천해줘." +
                        "assistant role에 있는 카드는 []안에 정보가 한개의 카드 정보야. 다른 카드 정보랑 섞에서 출력하지 말아줘." +
                        "카드를 추천하는 채팅을 출력할 때는 카드이름은 따옴표 안에 출력해줘. 연회비 내용은 포함하지 말아줘.");

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