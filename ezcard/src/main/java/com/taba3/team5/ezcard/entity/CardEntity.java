package com.taba3.team5.ezcard.entity;

import com.taba3.team5.ezcard.dto.CardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "card_table")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardSeq;

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false)
    private String cardBank;

    @Column(nullable = false)
    private int cardMembership;

    @Column(nullable = false)
    private int cardRecord;

    @Column(nullable = false)
    private String cardSummary;

    @Column(nullable = false)
    private String cardImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    public enum CardType {
        CREDIT, DEBIT
    }

    public static CardEntity fromCardDTO(CardDTO cardDTO) {
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

    public static CardDTO toCardDTO(CardEntity cardEntity) {
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
