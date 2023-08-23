package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.dto.home.HomeCardDto;
import com.taba3.team5.ezcard.dto.home.HomeCardResponse;
import com.taba3.team5.ezcard.service.HomeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class HomeCardController {

    private final HomeCardService homeCardService;

    @Autowired
    public HomeCardController(HomeCardService homeCardService) {
        this.homeCardService = homeCardService;
    }

    @GetMapping("/home")
    public ResponseEntity<HomeCardResponse> homecard(HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        String age = homeCardService.findUserAgeByEmail(loginEmail);
        String nickname = homeCardService.findUserNickname(loginEmail);

        List<HomeCardDto> homeCardDtoList = homeCardService.homeCard(age);
        HomeCardResponse homeCardResponse = new HomeCardResponse();
        homeCardResponse.setHomeCardDtoList(homeCardDtoList, nickname);

        if (loginEmail != null) {
            return ResponseEntity.status(HttpStatus.OK).body(homeCardResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
