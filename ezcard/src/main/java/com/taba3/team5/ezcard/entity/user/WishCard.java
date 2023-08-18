package com.taba3.team5.ezcard.entity.user;

import com.taba3.team5.ezcard.entity.card.Card;

import javax.persistence.*;

@Entity
@Table(name = "wish_card_tb")
public class WishCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long wish_card_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    // Getters and setters
}

