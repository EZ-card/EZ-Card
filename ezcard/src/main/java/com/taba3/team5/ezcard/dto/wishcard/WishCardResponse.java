package com.taba3.team5.ezcard.dto.wishcard;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Data
public class WishCardResponse {

    private List<WishCardDto> wishCardDtoList;

    public void setWishCardList(List<WishCardDto> wishCardDtoList) {
        this.wishCardDtoList = wishCardDtoList;
    }
}
