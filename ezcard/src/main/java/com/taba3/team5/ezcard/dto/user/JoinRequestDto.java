package com.taba3.team5.ezcard.dto.user;

import lombok.Getter;

@Getter
public class JoinRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String age;
    private String gender;
    private String job;
}

