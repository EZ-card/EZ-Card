package com.taba3.team5.ezcard.entity.wishcard;

import com.taba3.team5.ezcard.entity.card.Card;
import com.taba3.team5.ezcard.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "wish_card_tb")
public class WishCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long wishId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card cardId;

    public Card getCard() {
        return cardId;
    }

    public void setCard(Card card) {
        this.cardId = card;
    }


}

