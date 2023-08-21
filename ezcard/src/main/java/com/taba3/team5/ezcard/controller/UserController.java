package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.dto.user.JoinRequestDto;
import com.taba3.team5.ezcard.dto.user.LoginRequestDto;
import com.taba3.team5.ezcard.dto.user.LoginUserDto;
import com.taba3.team5.ezcard.entity.user.User;
import com.taba3.team5.ezcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. 회원가입
    @PostMapping("/join")
    public ResponseEntity<String> save(@RequestBody JoinRequestDto joinRequestDto) {
        User user = User.toUser(joinRequestDto);
        userService.save(user);
        return ResponseEntity.ok("OK");
    }

    // 2. 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        LoginUserDto loginResult = userService.login(loginRequestDto);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getEmail());
            session.setAttribute("loginId", loginResult.getId());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            // login 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 3. 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("OK");
    }
}
