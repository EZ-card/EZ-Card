package com.taba3.team5.ezcard.entity.agecard;

import com.taba3.team5.ezcard.entity.card.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "age_card_tb")
public class AgeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "age_card_id")
    private int ageCardId;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "match_age")
    private String matchAge;
}
