package com.taba3.team5.ezcard.card.service;

import com.taba3.team5.ezcard.card.dto.CardDTO;
import com.taba3.team5.ezcard.card.dto.ChatCardDTO;
import com.taba3.team5.ezcard.card.entity.CardEntity;
import com.taba3.team5.ezcard.card.repository.CardRepository;
import com.taba3.team5.ezcard.member.dto.MemberDTO;
import com.taba3.team5.ezcard.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    @Autowired
    private final CardRepository cardRepository;

    public List<CardDTO> findAll() {
        List<CardEntity> cardEntityList = cardRepository.findAll();
        List<CardDTO> cardDTOList = new ArrayList<>();
        for (CardEntity cardEntity: cardEntityList) {
            cardDTOList.add(CardDTO.fromCardEntity(cardEntity));
        }
        return cardDTOList;
    }

    public CardDTO findById(Long id) {
        Optional<CardEntity> optionalCardEntity = cardRepository.findById(id);
        if (optionalCardEntity.isPresent()) {
            return CardDTO.fromCardEntity(optionalCardEntity.get());
        } else {
            return null;
        }

    }

    // 카드 이름으로 카드 정보 찾기
    // Assuming cardRepository is injected appropriately
    public CardDTO getCardInfoByCardName(String cardName) {
        CardEntity cardEntity = cardRepository.findOneByCardName(cardName);
        return CardDTO.fromCardEntity(cardEntity);
    }


}