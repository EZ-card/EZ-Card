package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.card.CardBenefitDto;
import com.taba3.team5.ezcard.dto.card.CardDto;
import com.taba3.team5.ezcard.dto.card.CardResponse;
import com.taba3.team5.ezcard.entity.card.Card;
import com.taba3.team5.ezcard.entity.card.CardRepository;
import com.taba3.team5.ezcard.entity.cardbenefit.CardBenefit;
import com.taba3.team5.ezcard.entity.cardbenefit.CardBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    public static CardRepository cardRepository;
    private final CardBenefitRepository cardBenefitRepository;

    @Autowired
    public CardService(CardRepository cardRepository, CardBenefitRepository cardBenefitRepository) {
        this.cardRepository = cardRepository;
        this.cardBenefitRepository = cardBenefitRepository;
    }

    //cardid를 받아서 카드 세부정보를 받아와 출력
    public CardResponse cardDetail(Long cardId) {
        CardResponse cardResponse = new CardResponse();
        CardDto cardDto = findCardById(cardId);
        List<CardBenefitDto> cardBenefitDtoList = CardBenefitService.findBenefitsByCardId(cardId);

        cardResponse.setCardDto(cardDto);
        cardResponse.setCardBenefitList(cardBenefitDtoList);

        return cardResponse;
    }

    //cardname으로 검색하여 carddto 반환
    public static CardDto findCardByName(String cardName) {
        Optional<Card> cardOptional = cardRepository.findOneByCardName(cardName);

        if (cardOptional.isPresent()) {
            Card card = cardOptional.get();
            return CardDto.fromEntity(card);
        } else {
            // 카드가 없을 경우 예외 처리 또는 다른 방법을 선택할 수 있습니다.
            return null;
        }
    }

    //cardid로 검색하여 carddto 반환
    public CardDto findCardById(Long cardId) {
        Optional<Card> cardOptional = cardRepository.findOneByCardId(cardId);

        if (cardOptional.isPresent()) {
            Card card = cardOptional.get();
            return CardDto.fromEntity(card);
        } else {
            // 카드가 없을 경우 예외 처리 또는 다른 방법을 선택할 수 있습니다.
            return null;
        }
    }


}
