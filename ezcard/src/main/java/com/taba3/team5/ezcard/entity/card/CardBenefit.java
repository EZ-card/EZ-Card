package com.taba3.team5.ezcard.entity.card;

import javax.persistence.*;

@Entity
@Table(name = "benefit_tb")
public class CardBenefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benefit_id")
    private Long benefitId;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "benefit_category")
    private String benefitCategory;

    @Column(name = "benefit_summary")
    private String benefitSummary;

    @Column(name = "benefit_detail")
    private String benefitDetail;

    // Getters and setters
}
