package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.chatbot.ChatBotResponse;
import com.taba3.team5.ezcard.dto.card.CardDto;
import com.taba3.team5.ezcard.model.GptApiClient;
import com.taba3.team5.ezcard.model.GptModel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;


@Service
public class ChatBotService {
    private final CardService cardService;
    private final GptApiClient gptApiClient;
    private final Queue<String> chatHistory; // 대화 히스토리를 관리하기 위한 큐
    private static final int MAX_HISTORY_SIZE = 2; // 대화 히스토리 크기 제한

    @Autowired
    public ChatBotService(CardService cardService, GptApiClient gptApiClient) {
        this.cardService = cardService;
        this.gptApiClient = gptApiClient;
        this.chatHistory = new LinkedList<>(); // 큐로 초기화
    }

    public ChatBotResponse ezbot(String userinput) {
        ChatBotResponse chatBotResponse = new ChatBotResponse();

        // 이전 Assistant 응답을 가져옴
        String previousAssistantResponse = getPreviousAssistantResponse();

        // 대화 히스토리에 현재 대화 추가
        chatHistory.offer(previousAssistantResponse);

        // 대화 히스토리 크기를 제한
        while (chatHistory.size() > MAX_HISTORY_SIZE) {
            chatHistory.poll(); // 가장 오래된 대화 제거
        }

        // 대화 히스토리를 JSON 형식으로 저장
        String chatHistoryJson = String.join("\n", chatHistory);

        JSONObject jsonBody = GptModel.createJsonBody(userinput, chatHistoryJson);

        try {
            String responseFrompt = gptApiClient.getGptResponse(jsonBody);
            String gptResponse = extractAnswer(responseFrompt);

            String cardName = extractCardNames(gptResponse); //카드이름 받아오기

            CardDto cardDto = cardService.findCardByName(cardName); //카드 정보를 이름으로 검색하여 저장

            if (cardDto != null) {
                // 카드 정보를 사용하여 응답 생성
                chatBotResponse.setGptResponse(gptResponse); //응답 컨테이너에 gpt답변 저장
                chatBotResponse.setCardDto(cardDto); //응답 컨테이너에 카드정보 저장
            }
            // 카드 추천이 아닌 다른 응답 생성
            chatBotResponse.setGptResponse(gptResponse);


            // 대화 히스토리에 현재 Assistant 응답 추가
            chatHistory.offer("GPT: " + gptResponse);

            return chatBotResponse;

        } catch (Exception e) {
            e.printStackTrace();
            chatBotResponse.setGptResponse("오류가 발생했습니다.");
        }

        return chatBotResponse;
    }

    // 이전 Assistant 응답을 가져오는 함수
    private String getPreviousAssistantResponse() {
        if (chatHistory.isEmpty()) {
            return ""; // 이전 응답이 없음
        }

        // 가장 최근의 Assistant 응답을 반환
        String latestEntry = chatHistory.peek();
        if (latestEntry.startsWith("GPT: ")) {
            return latestEntry.substring(4); // "GPT: " 부분을 제거
        } else {
            return "";
        }
    }

    //gpt답변에서 카드이름 추출
    private String extractCardNames(String gptResponse) {
        // 정규 표현식 패턴 설정
        String pattern1 = "카드이름:\\s*(.*?)(?:\\s*,|\\s*/|\\n)";
        Pattern r1 = Pattern.compile(pattern1);

        Matcher matcher1 = r1.matcher(gptResponse);
        if (matcher1.find()) {
            String cardName1 = matcher1.group(1);
            return cardName1;
        }

        String pattern2 = "\"(.*?)\"";
        Pattern r2 = Pattern.compile(pattern2);

        Matcher matcher2 = r2.matcher(gptResponse);
        if (matcher2.find()) {
            String cardName2 = matcher2.group(1);
            return cardName2;
        }

        return "카드 이름을 추출할 수 없습니다.";
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
    // ChatBotService 클래스 내부에 세션을 초기화하는 메서드 추가
    public void clearChatHistory() {
        chatHistory.clear(); // 대화 히스토리를 초기화
    }

    // 웹 애플리케이션에서 사용자가 페이지를 나갈 때나 다른 페이지로 이동할 때 호출하는 메서드
    public void handlePageNavigation(HttpSession session) {
        clearChatHistory(); // 대화 히스토리 초기화
        session.removeAttribute("chatHistory"); // 세션에서 대화 히스토리 제거
    }
}