package com.taba3.team5.ezcard.dto.card;

import com.taba3.team5.ezcard.entity.card.Card;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class CardDto {
    private Long cardId;
    private String cardName;
    private String cardBank;
    private String cardMembership;
    private String cardRecord;
    private String cardSummary1;
    private String cardSummary2;
    private String cardSummary3;
    private String cardImage;
    private String cardType;
    private String cardLink;

    public static CardDto fromEntity(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setCardId(card.getCardId());
        cardDto.setCardName(card.getCardName());
        cardDto.setCardBank(card.getCardBank());
        cardDto.setCardMembership(card.getCardMembership());
        cardDto.setCardRecord(card.getCardRecord());
        cardDto.setCardSummary1(card.getCardSummary1());
        cardDto.setCardSummary2(card.getCardSummary2());
        cardDto.setCardSummary3(card.getCardSummary3());
        cardDto.setCardImage(card.getCardImage());
        cardDto.setCardType(card.getCardType());
        cardDto.setCardLink(card.getCardLink());

        return cardDto;
    }
}
