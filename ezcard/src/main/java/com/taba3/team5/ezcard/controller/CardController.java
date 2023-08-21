package com.taba3.team5.ezcard.controller;
import com.taba3.team5.ezcard.dto.card.CardResponse;
import com.taba3.team5.ezcard.service.CardBenefitService;
import com.taba3.team5.ezcard.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final CardBenefitService cardBenefitService;

    @Autowired
    public CardController(CardService cardService, CardBenefitService cardBenefitService) {
        this.cardService = cardService;
        this.cardBenefitService = cardBenefitService;
    }

    @GetMapping("/detail/{cardid}")
    public ResponseEntity<CardResponse> cardDetail(@PathVariable Long cardid) {
        CardResponse cardResponse = cardService.cardDetail(cardid);

        if (cardResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cardResponse);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


