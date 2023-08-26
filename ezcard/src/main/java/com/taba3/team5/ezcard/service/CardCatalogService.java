package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.card.CardCatalogDto;
import com.taba3.team5.ezcard.entity.card.Card;
import com.taba3.team5.ezcard.entity.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardCatalogService {
    private final CardRepository cardRepository;

    @Autowired
    public CardCatalogService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardCatalogDto> cardCatalogDtoList() {
        List<Card> cards = cardRepository.findAll();
        List<CardCatalogDto> cardCatalogDtoList = new ArrayList<>();

        for (Card card : cards) {
            CardCatalogDto cardCatalogDto = new CardCatalogDto();
            cardCatalogDto.setCardId(card.getCardId());
            cardCatalogDto.setCardName(card.getCardName());
            cardCatalogDto.setCardBank(card.getCardBank());
            cardCatalogDto.setCardImage(card.getCardImage());
            cardCatalogDto.setCardType(card.getCardType());
            cardCatalogDtoList.add(cardCatalogDto);
        }

        return cardCatalogDtoList;
    }
}

