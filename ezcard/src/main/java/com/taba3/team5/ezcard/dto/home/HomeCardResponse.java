package com.taba3.team5.ezcard.dto.home;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HomeCardResponse {
    private List<HomeCardDto> homeCardDtoList;
    private String nickname;

    public void setHomeCardDtoList(List<HomeCardDto> homeCardDtoList, String nickname) {
        this.homeCardDtoList = homeCardDtoList;
        this.nickname = nickname;
    }
}
