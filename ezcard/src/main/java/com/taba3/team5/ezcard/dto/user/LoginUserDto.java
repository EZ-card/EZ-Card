package com.taba3.team5.ezcard.dto.user;

import com.taba3.team5.ezcard.entity.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginUserDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String age;
    private String gender;
    private String job;

    public static LoginUserDto toUserDto(User user) {
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setId(user.getUserId());
        loginUserDto.setEmail(user.getUserEmail());
        loginUserDto.setPassword(user.getUserPwd());
        loginUserDto.setNickname(user.getUserNickname());
        loginUserDto.setAge(user.getUserAge());
        loginUserDto.setGender(user.getUserGender());
        loginUserDto.setJob(user.getUserJob());
        return loginUserDto;
    }
}
