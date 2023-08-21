package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.dto.wishcard.WishCardDto;
import com.taba3.team5.ezcard.dto.wishcard.WishCardResponse;
import com.taba3.team5.ezcard.entity.user.User;
import com.taba3.team5.ezcard.service.UserService;
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
    private final UserService userService;

    @Autowired
    public WishCardController(WishCardService wishCardService, UserService userService) {
        this.wishCardService = wishCardService;
        this.userService = userService;
    }

    @GetMapping("/wish")
    public ResponseEntity<WishCardResponse> wishCardList(HttpSession session) {
        Long userId = (Long) session.getAttribute("loginId");
        List<WishCardDto> wishCardDtoList = wishCardService.wishCardDtoList(userId);
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
            Long userid = (Long) session.getAttribute("loginId");
            // 위에서 얻은 userid와 cardid를 사용하여 Wish Card를 추가합니다.
            wishCardService.addWishCard(userid, cardid);

            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding Wish Card.");
        }
    }

    @DeleteMapping("/wish/{cardid}")
    public ResponseEntity<String> wishDelete(@PathVariable Long cardid,HttpSession session) {
        Long userid = (Long) session.getAttribute("loginId");
        return wishCardService.deleteWishCard(userid, cardid);
    }
}


