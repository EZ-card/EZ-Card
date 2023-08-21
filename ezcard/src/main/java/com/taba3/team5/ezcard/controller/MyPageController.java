package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.dto.mypage.MyPageRequestDto;
import com.taba3.team5.ezcard.dto.mypage.MyPageResponseDto;
import com.taba3.team5.ezcard.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // 마이페이지 - 회원 정보 수정
    @PutMapping(path = "/mypage")
    public ResponseEntity<String> updateUserInformation(@RequestBody MyPageRequestDto myPageRequestDto, HttpSession session) {
        Long loginId = (Long) session.getAttribute("loginId");
        boolean isUpdated = myPageService.updateUserInformation(loginId, myPageRequestDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("User information updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user information.");
        }
    }
}
