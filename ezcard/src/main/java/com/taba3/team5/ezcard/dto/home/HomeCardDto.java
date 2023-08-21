package com.taba3.team5.ezcard.dto.home;

import com.taba3.team5.ezcard.entity.card.Card;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Data
public class HomeCardDto {
    private String cardName;
    private String cardBank;
    private String cardSummary;
    private String cardImage;

    public static HomeCardDto fromEntity(Card card) {
        HomeCardDto homeCardDto = new HomeCardDto();
        homeCardDto.setCardName(card.getCardName());
        homeCardDto.setCardBank(card.getCardBank());
        homeCardDto.setCardSummary(card.getCardSummary());

        return homeCardDto;
    }
}
