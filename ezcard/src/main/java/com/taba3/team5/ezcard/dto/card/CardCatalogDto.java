package com.taba3.team5.ezcard.dto.card;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class CardCatalogDto {
    private Long cardId;
    private String cardName;
    private String cardBank;
    private String cardImage;
    private String cardType;
}
