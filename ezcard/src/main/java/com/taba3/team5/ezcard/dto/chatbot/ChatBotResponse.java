package com.taba3.team5.ezcard.dto.chatbot;
import com.taba3.team5.ezcard.dto.card.CardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatBotResponse {
    private String gptResponse;
    private CardDto cardDto;

    public void setGptResponse(String gptResponse) {

        this.gptResponse = gptResponse;
    }
    public void setCardDto(CardDto cardDto) {

        this.cardDto = cardDto;
    }
}