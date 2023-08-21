package com.taba3.team5.ezcard.service;

import com.taba3.team5.ezcard.dto.card.CardResponse;
import com.taba3.team5.ezcard.dto.wishcard.WishCardDto;
import com.taba3.team5.ezcard.entity.card.Card;
import com.taba3.team5.ezcard.entity.user.User;
import com.taba3.team5.ezcard.entity.user.UserRepository;
import com.taba3.team5.ezcard.entity.wishcard.WishCard;
import com.taba3.team5.ezcard.entity.wishcard.WishCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.taba3.team5.ezcard.service.CardService.cardRepository;

@Service
public class WishCardService {

    private final WishCardRepository wishCardRepository;
    private final UserRepository userRepository;

    @Autowired
    public WishCardService(WishCardRepository wishCardRepository, UserRepository userRepository) {

        this.wishCardRepository = wishCardRepository;
        this.userRepository = userRepository;
    }

    public List<WishCardDto> wishCardDtoList(Long userId) {

        User userid = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<WishCard> wishCards = wishCardRepository.findByUserId(userid);
        List<WishCardDto> wishCardDtoList = new ArrayList<>();

        for (WishCard wishCard : wishCards) {
            WishCardDto wishCardDto = new WishCardDto();

            // Card 엔티티에서 정보를 추출하여 WishCardDto에 설정
            Card card = wishCard.getCard();
            wishCardDto.setCardName(card.getCardName());
            wishCardDto.setCardBank(card.getCardBank());
            wishCardDto.setCardImage(card.getCardImage());

            wishCardDtoList.add(wishCardDto);
        }
        return wishCardDtoList;
    }


    //위시카드 추가
    public void addWishCard(Long userId, Long cardId) {
        // userId와 cardId를 사용하여 WishCard 엔티티를 생성하고 저장합니다.
        WishCard wishCard = new WishCard();

        // userId와 cardId를 사용하여 실제 User 엔티티와 Card 엔티티를 가져와서 설정합니다.
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found"));

        wishCard.setUserId(user);
        wishCard.setCardId(card);

        wishCardRepository.save(wishCard);
    }

    //위시카드 삭제
    @Transactional
    public ResponseEntity<String> deleteWishCard(Long userId, Long cardId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found"));

        WishCard wishCard = wishCardRepository.findByCardIdAndUserId(card, user);
        if (wishCard != null) {
            wishCardRepository.delete(wishCard);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException("WishCard not found.");
        }
    }

    public boolean checkWishCard(Long cardId, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found"));

        boolean wishCardExists = wishCardRepository.findByCardIdAndUserId(card, user) != null;

        return wishCardExists;
    }

}

