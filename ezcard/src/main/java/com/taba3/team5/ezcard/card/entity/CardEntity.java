package com.taba3.team5.ezcard.card.entity;

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
}
