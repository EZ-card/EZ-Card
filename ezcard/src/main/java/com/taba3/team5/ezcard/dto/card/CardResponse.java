package com.taba3.team5.ezcard.dto.card;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CardResponse {

    private CardDto cardDto;
    private List<CardBenefitDto> cardBenefitList;

    public void setCardDto(CardDto cardDto) {
        this.cardDto = cardDto;
    }
    public void setCardBenefitList(List<CardBenefitDto> cardBenefitList) {
        this.cardBenefitList = cardBenefitList;
    }
}
