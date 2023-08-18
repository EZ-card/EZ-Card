package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.GptApiClient;
import com.taba3.team5.ezcard.dto.Chat.ChatResponseDto;
import com.taba3.team5.ezcard.entity.CardEntity;
import com.taba3.team5.ezcard.model.GptModel;
import com.taba3.team5.ezcard.dto.CardDTO;
import com.taba3.team5.ezcard.repository.CardRepository;
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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
public class ChatService {
    @Value("${key.gpt}")
    private String API_KEY;

    private final CardService cardService;
    private final GptApiClient gptApiClient;

    public ChatService(CardService cardService, GptApiClient gptApiClient) {
        this.cardService = cardService;
        this.gptApiClient = gptApiClient;
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

        JSONObject jsonBody = GptModel.createJsonBody(prompt);

        try {
            String responseFrompt = gptApiClient.getGptResponse(jsonBody);
            responseBody = extractAnswer(responseFrompt);
            responseList.add(responseBody);

            // "카드추천해줘" 문장이 있는 경우, 카드 추천 작업 수행
            if (prompt.contains("카드") && prompt.contains("추천")) {
                List<String> cardNames = extractCardNames(responseBody); //카드이름이 담겨있는 리스트 받아오기

                List<ChatResponseDto> chatDots = new ArrayList<>();//카드 정보를 담을
                List<CardEntity> cardList = CardRepository.findOneByCardName(cardNames);

                for (String cardName : cardNames) {
                    CardDTO cardDTO = cardService.getCardInfoByCardName(cardName);
                    ChatResponseDto responseDto = new ChatResponseDto(cardName);

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