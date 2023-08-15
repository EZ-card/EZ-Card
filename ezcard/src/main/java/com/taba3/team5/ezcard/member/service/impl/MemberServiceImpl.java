package com.taba3.team5.ezcard.member.service.impl;

import com.taba3.team5.ezcard.member.dto.MemberDto;
import com.taba3.team5.ezcard.member.entity.Member;
import com.taba3.team5.ezcard.member.repository.MemberRepository;
import com.taba3.team5.ezcard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public Long signup(MemberDto memberDto) {
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .age(memberDto.getAge())
                .gender(memberDto.getGender())
                .job(memberDto.getJob())
                .build();
        return memberRepository.save(member).getId();
    }
}
