package com.taba3.team5.ezcard.entity.card;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "card_tb")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_bank")
    private String cardBank;

    @Column(name = "card_membership")
    private String cardMembership;

    @Column(name = "card_record")
    private String cardRecord;

    @Column(name = "card_summary1")
    private String cardSummary1;

    @Column(name = "card_summary2")
    private String cardSummary2;

    @Column(name = "card_summary3")
    private String cardSummary3;

    @Column(name = "card_image")
    private String cardImage;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_link")
    private String cardLink;

}

