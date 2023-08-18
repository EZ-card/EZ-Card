package com.taba3.team5.ezcard.entity.card;

import javax.persistence.*;

@Entity
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

    @Column(name = "card_summary")
    private String cardSummary;

    @Column(name = "card_image")
    private String cardImage;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_link")
    private String cardLink;

    // Getters and setters
}

