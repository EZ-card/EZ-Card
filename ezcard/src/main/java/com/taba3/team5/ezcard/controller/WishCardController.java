package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.dto.wishcard.WishCardDto;
import com.taba3.team5.ezcard.dto.wishcard.WishCardResponse;
import com.taba3.team5.ezcard.service.WishCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class WishCardController {

    private final WishCardService wishCardService;

    @Autowired
    public WishCardController(WishCardService wishCardService) {
        this.wishCardService = wishCardService;
    }

    @GetMapping("/wish/{userid}")
    public ResponseEntity<WishCardResponse> wishCardList(@PathVariable Long userid) {
        List<WishCardDto> wishCardDtoList = wishCardService.wishCardDtoList(userid);
        WishCardResponse wishCardResponse = new WishCardResponse();
        wishCardResponse.setWishCardList(wishCardDtoList);

        if (wishCardDtoList != null) {
            return ResponseEntity.status(HttpStatus.OK).body(wishCardResponse);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/wish/{cardid}")
    public ResponseEntity<String> wishAdd(@PathVariable Long cardid, HttpSession session) {
        try {
            // 세션에서 userid를 가져옵니다.
            Long userid = (Long) session.getAttribute("userid");

            // 위에서 얻은 userid와 cardid를 사용하여 Wish Card를 추가합니다.
            wishCardService.addWishCard(userid, cardid);

            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding Wish Card.");
        }
    }

    @DeleteMapping("/wish/{userid}/{cardid}")
    public ResponseEntity<String> wishDelete(
            @PathVariable Long userid,
            @PathVariable Long cardid)
    {
        return wishCardService.deleteWishCard(userid, cardid);
    }
}


