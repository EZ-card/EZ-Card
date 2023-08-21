package com.taba3.team5.ezcard.dto.mypage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageRequestDto {
    private String password;
    private String nickname;
    private String age;
    private String gender;
    private String job;
}
