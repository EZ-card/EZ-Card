package com.taba3.team5.ezcard.dto.Chat;

import com.taba3.team5.ezcard.dto.CardDTO;
import com.taba3.team5.ezcard.entity.CardEntity;
import lombok.Getter;

@Getter
public class ChatResponseDto {

    private String cardName;
    private String cardBank;
    private int cardMembership;
    private int cardRecord;
    private String cardSummary;
    private CardEntity.CardType cardType;

    public ChatResponseDto (CardEntity cardEntity) {
        this.cardName = cardEntity.getCardName();

}

    public ChatResponseDto(String cardName) {
    }
