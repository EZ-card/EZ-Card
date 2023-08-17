package com.taba3.team5.ezcard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "card_benefit_table")
public class CardBenefitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long benefitSeq;

    @ManyToOne
    @JoinColumn(name = "card_seq", referencedColumnName = "cardSeq")
    private CardEntity card;

    @Column
    private String benefitCategory;

    @Column
    private String benefitDetail;

    @Column
    private String benefitSummary;
}
