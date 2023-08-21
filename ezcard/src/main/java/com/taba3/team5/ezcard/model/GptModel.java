package com.taba3.team5.ezcard.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//gpt의 시스템 role 설정과 채팅 모델의 설정을 관리하는 서비스
public class GptModel {

    public static JSONObject createJsonBody(String userinput) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("messages", new JSONArray()
                .put(new JSONObject()
                        .put("role", "assistant") // 어시스턴트 역할 추가
                        .put("content", CardInfo.MESSAGE))
                .put(new JSONObject()
                        .put("role", "system")
                        .put("content",
                                "카드추천해줘라고 입력받으면 assistant 대화기록 중에 카드 1개만 골라서 추천해줘 " +
                                "추천 형식은 카드이름: /추천이유: 로 1줄로 짧게 해줘."))
                .put(new JSONObject()
                        .put("role", "user")
                        .put("content", userinput)));
        jsonBody.put("max_tokens", 2000);
        jsonBody.put("temperature", 0.0);
        jsonBody.put("model", "gpt-3.5-turbo");
        return jsonBody;
    }

    public static List<String> splitConversationIntoChunks(String conversation, int chunkSize) {
        List<String> chunks = new ArrayList<>();
        String[] messages = conversation.split("\n"); // 대화를 메시지로 분할

        StringBuilder currentChunk = new StringBuilder();
        for (String message : messages) {
            if (currentChunk.length() + message.length() <= chunkSize) {
                // 현재 청크에 추가 가능한 경우 추가
                currentChunk.append(message).append("\n");
            } else {
                // 새로운 청크를 시작
                chunks.add(currentChunk.toString());
                currentChunk = new StringBuilder(message).append("\n");
            }
        }

        if (currentChunk.length() > 0) {
            chunks.add(currentChunk.toString());
        }

        return chunks;
    }
}