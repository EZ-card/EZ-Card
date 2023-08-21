package com.taba3.team5.ezcard.dto.mypage;

import com.taba3.team5.ezcard.entity.user.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyPageResponseDto {
    private String email;
    private String password;
    private String nickname;
    private String age;
    private String gender;
    private String job;

    public static MyPageResponseDto toMypageUserDto(User user) {
        MyPageResponseDto myPageResponseDto = new MyPageResponseDto();
        myPageResponseDto.setEmail(user.getUserEmail());
        myPageResponseDto.setPassword(user.getUserPwd());
        myPageResponseDto.setNickname(user.getUserNickname());
        myPageResponseDto.setAge(user.getUserAge());
        myPageResponseDto.setGender(user.getUserGender());
        myPageResponseDto.setJob(user.getUserJob());
        return myPageResponseDto;
    }
}
