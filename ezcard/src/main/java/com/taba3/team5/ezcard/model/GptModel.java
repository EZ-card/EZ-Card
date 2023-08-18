package com.taba3.team5.ezcard.model;

import com.taba3.team5.ezcard.CardInfo;
import org.json.JSONArray;
import org.json.JSONObject;


//gpt의 시스템 role 설정과 채팅 모델의 설정을 관리하는 서비스
public class GptModel {

    public static JSONObject createJsonBody(String prompt) {
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
        return jsonBody;
    }

}
