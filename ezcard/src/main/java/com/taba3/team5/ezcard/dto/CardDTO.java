package com.taba3.team5.ezcard.dto;

import com.taba3.team5.ezcard.entity.CardEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardDTO {
    private Long cardSeq;
    private String cardName;
    private String cardBank;
   private int cardMembership;
    private int cardRecord;
    private String cardSummary;
    private String cardImage;
    private CardEntity.CardType cardType;

//    private List<String> cardNames;


    public static CardEntity toCardEntity(CardDTO cardDTO) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardSeq(cardDTO.getCardSeq());
        cardEntity.setCardName(cardDTO.getCardName());
        cardEntity.setCardBank(cardDTO.getCardBank());
        cardEntity.setCardMembership(cardDTO.getCardMembership());
        cardEntity.setCardRecord(cardDTO.getCardRecord());
        cardEntity.setCardSummary(cardDTO.getCardSummary());
        cardEntity.setCardImage(cardDTO.getCardImage());
        cardEntity.setCardType(cardDTO.getCardType());
        return cardEntity;
    }

    public static CardDTO fromCardEntity(CardEntity cardEntity) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardSeq(cardEntity.getCardSeq());
        cardDTO.setCardName(cardEntity.getCardName());
        cardDTO.setCardBank(cardEntity.getCardBank());
        cardDTO.setCardMembership(cardEntity.getCardMembership());
        cardDTO.setCardRecord(cardEntity.getCardRecord());
        cardDTO.setCardSummary(cardEntity.getCardSummary());
        cardDTO.setCardImage(cardEntity.getCardImage());
        cardDTO.setCardType(cardEntity.getCardType());
        return cardDTO;
    }
}
