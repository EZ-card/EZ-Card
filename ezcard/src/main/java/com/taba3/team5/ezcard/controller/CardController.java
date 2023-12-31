package com.taba3.team5.ezcard.controller;
import com.taba3.team5.ezcard.dto.card.CardCatalogDto;
import com.taba3.team5.ezcard.dto.card.CardCatalogListResponse;
import com.taba3.team5.ezcard.dto.card.CardDto;
import com.taba3.team5.ezcard.dto.card.CardResponse;
import com.taba3.team5.ezcard.entity.card.Card;
import com.taba3.team5.ezcard.service.CardBenefitService;
import com.taba3.team5.ezcard.service.CardCatalogService;
import com.taba3.team5.ezcard.service.CardService;
import com.taba3.team5.ezcard.service.WishCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;
    private final CardBenefitService cardBenefitService;
    private final WishCardService wishCardService;
    private final CardCatalogService cardCatalogService;

    @Autowired
    public CardController(CardService cardService, CardBenefitService cardBenefitService, WishCardService wishCardService, CardCatalogService cardCatalogService) {
        this.cardService = cardService;
        this.cardBenefitService = cardBenefitService;
        this.wishCardService = wishCardService;
        this.cardCatalogService = cardCatalogService;
    }

    @GetMapping("/detail/{cardid}")
    public ResponseEntity<CardResponse> cardDetail(@PathVariable Long cardid, HttpSession session) {
        CardResponse cardResponse = cardService.cardDetail(cardid);

        Long userId = (Long) session.getAttribute("loginId");

        // 사용자 ID가 null이 아니라면
        if (userId != null) {
            // checkWish 메서드 호출
            boolean wishCardExists = wishCardService.checkWishCard(cardid, userId);
            cardResponse.setWishCardExists(wishCardExists);
        }

        if (cardResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cardResponse);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/catalog")
    public ResponseEntity<CardCatalogListResponse> cardCatalog() {
        List<CardCatalogDto> cardCatalogDtoList = cardCatalogService.cardCatalogDtoList();
        CardCatalogListResponse response = new CardCatalogListResponse();
        response.setCardCatalogDtoList(cardCatalogDtoList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}


