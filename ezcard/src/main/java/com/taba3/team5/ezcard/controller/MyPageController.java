package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.dto.mypage.MyPageResponseDto;
import com.taba3.team5.ezcard.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MyPageController {
    private final MyPageService myPageService;
    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping(path = "/mypage")
    @ResponseBody
    public ResponseEntity<MyPageResponseDto> findByEmail(HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MyPageResponseDto myPageResponseDto = myPageService.findByEmail(loginEmail);

        if (myPageResponseDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(myPageResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
