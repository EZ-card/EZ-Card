package com.taba3.team5.ezcard.model;

import org.json.JSONArray;
import org.json.JSONObject;


//gpt의 시스템 role 설정과 채팅 모델의 설정을 관리하는 서비스
public class GptModel {

    public static JSONObject createJsonBody(String userinput) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("messages", new JSONArray()
                .put(new JSONObject()
                        .put("role", "system")
                        .put("content",
                                "카드추천해줘라고 입력받으면 대화기록 중에 카드 1개만 골라서 추천해줘 " +
                                "추천 형식은 카드이름: /추천이유: 로 1줄로 짧게 해줘."))
                .put(new JSONObject()
                        .put("role", "assistant") // 어시스턴트 역할 추가
                        .put("content", CardInfo.MESSAGE))
                .put(new JSONObject()
                        .put("role", "user")
                        .put("content", userinput)));
        jsonBody.put("max_tokens", 2000);
        jsonBody.put("temperature", 0.0);
        jsonBody.put("model", "gpt-3.5-turbo");
        return jsonBody;
    }

}