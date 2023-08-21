package com.taba3.team5.ezcard.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private int statusCode;
    private String message;
}
