package com.taba3.team5.ezcard.member.controller;

import com.taba3.team5.ezcard.member.dto.MemberDto;
import com.taba3.team5.ezcard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String Home() {
        return "home";
    }

    @GetMapping("/member/signup")
    public String Signup() {
        return "member/signup";
    }

    @PostMapping("/member/signup")
    public String createMember(MemberDto memberDto) {
        Long memberId = memberService.signup(memberDto);
        return "home";
    }
}
