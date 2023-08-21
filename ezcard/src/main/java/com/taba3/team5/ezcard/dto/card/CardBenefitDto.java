package com.taba3.team5.ezcard.dto.card;

import com.taba3.team5.ezcard.entity.cardbenefit.CardBenefit;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Getter
@NoArgsConstructor
@Data
public class CardBenefitDto {
    private String benefitCategory;
    private String benefitSummary;
    private String benefitDetail;

    public static CardBenefitDto fromEntity(CardBenefit cardBenefit) {
        CardBenefitDto cardBenefitDto = new CardBenefitDto();
        cardBenefitDto.setBenefitCategory(cardBenefit.getBenefitCategory());
        cardBenefitDto.setBenefitSummary(cardBenefit.getBenefitSummary());
        cardBenefitDto.setBenefitDetail(cardBenefit.getBenefitDetail());

        return cardBenefitDto;
    }
}
