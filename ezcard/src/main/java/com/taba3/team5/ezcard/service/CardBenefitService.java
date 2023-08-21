package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.card.CardBenefitDto;
import com.taba3.team5.ezcard.entity.cardbenefit.CardBenefit;
import com.taba3.team5.ezcard.entity.cardbenefit.CardBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardBenefitService {
    private static CardBenefitRepository cardBenefitRepository;

    @Autowired
    public CardBenefitService(CardBenefitRepository cardBenefitRepository) {
        this.cardBenefitRepository = cardBenefitRepository;
    }

    @Autowired
    public static List<CardBenefitDto> findBenefitsByCardId(Long cardId) {
        List<CardBenefitDto> cardBenefitDtoList = new ArrayList<>();

        List<CardBenefit> cardBenefits = cardBenefitRepository.findAllByCard_CardId(cardId);

        for (CardBenefit cardBenefit : cardBenefits) {
            cardBenefitDtoList.add(CardBenefitDto.fromEntity(cardBenefit));
        }

        return cardBenefitDtoList;
    }

}
