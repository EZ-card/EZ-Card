package com.taba3.team5.ezcard.dto.wishcard;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class WishCardDto {
    private Long cardId;
    private String cardName;
    private String cardBank;
    private String cardImage;
}
