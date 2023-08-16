package com.taba3.team5.ezcard.ezbot.service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class OpenAIApi {
    private static final String API_KEY = "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";

    private List<String> extractCardNames(String gptResponse) {
        List<String> cardNames = new ArrayList<>();
        String cardNamePrefix = "카드이름 : ";
        int startIndex = gptResponse.indexOf(cardNamePrefix);

        while (startIndex != -1) {
            int endIndex = gptResponse.indexOf(",", startIndex);
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

    public  String ezbot(String prompt) {
        String reseponseBody = "";
        String cardInfo = "";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("messages", new JSONArray()
                .put(new JSONObject()
                        .put("role", "system")
                        .put("content", CardInfo.MESSAGE +
                                        "너는 지금 주어진 카드 중 카드를 3개 추천해주는 상담사야. " +
                                        "형식은 '카드이름 : /카드추천 이유 : '으로 해줘. 추천 이유는 1줄로 짧게 해줘. " +
                                        "카드추천해줘라고 말했을 때만 카드 추천하고 아닐 때는 그냥 대화해."))
                .put(new JSONObject()
                        .put("role", "user")
                        .put("content", prompt)));
        jsonBody.put("max_tokens",2000);
        jsonBody.put("temperature",0.0);
        jsonBody.put("model", "gpt-3.5-turbo");

        try {
            // 새로운 HttpClient 인스턴스를 생성합니다.
            HttpClient client = HttpClient.newHttpClient();

            // 요청을 위한 HttpRequest를 빌드합니다.
            HttpRequest request = HttpRequest.newBuilder()
                    // 요청할 URI를 생성합니다.
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    // 요청 헤더에 Content-Type을 설정합니다.
                    .header("Content-Type", "application/json")
                    // 요청 헤더에 Authorization 토큰을 설정합니다.
                    .header("Authorization", "Bearer " + API_KEY)
                    // POST 요청을 생성하고, 요청 본문에 JSON 데이터를 포함합니다.
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody.toString()))
                    .build();

            // 요청을 보내고, 응답을 받아옵니다.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 받아온 응답 본문을 분석하여 처리합니다.
            reseponseBody = extractAnswer(response.body());

        } catch (Exception e) {
            // 예외 발생 시에는 해당 예외를 출력하여 디버깅하거나 예외 처리를 진행합니다.
            e.printStackTrace();
        }

// 처리된 응답 본문을 반환합니다.
        return reseponseBody;

    }

    private String extractAnswer(String responseJson) {
        // 받아온 응답 JSON 문자열을 JSONObject로 변환합니다.
        JSONObject jsonObject = new JSONObject(responseJson);

        // JSON 객체 내에 "choices" 키가 있는지 확인합니다.
        if (jsonObject.has("choices")) {
            // "choices" 키에 해당하는 값을 JSONArray로 가져옵니다.
            JSONArray choicesArray = jsonObject.getJSONArray("choices");

            // JSONArray 내에서 첫 번째 객체의 "message"를 JSONObject로 가져옵니다.
            JSONObject messageObject = choicesArray.getJSONObject(0).getJSONObject("message");

            // "message" 객체 내의 "content" 키에 해당하는 값을 가져와 공백을 제거한 뒤 반환합니다.
            return messageObject.getString("content").trim();
        } else {
            // "choices" 키가 없을 경우에는 오류 메시지를 출력하고 오류 메시지를 반환합니다.
            System.err.println("API 응답에서 오류 발생: " + responseJson);
            return "API 호출 중 오류가 발생했습니다.";
        }
    }

}
