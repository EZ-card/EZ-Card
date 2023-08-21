package com.taba3.team5.ezcard.dto.user;

import com.taba3.team5.ezcard.entity.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequestDto {
    private String email;
    private String password;

    public static LoginRequestDto toLoginRequestDto(User user) {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(user.getUserEmail());
        loginRequestDto.setPassword(user.getUserPwd());
        return loginRequestDto;
    }
}
