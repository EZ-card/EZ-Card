package com.taba3.team5.ezcard.dto.home;

import com.taba3.team5.ezcard.entity.card.Card;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Data
public class HomeCardDto {
    private Long cardId;
    private String cardName;
    private String cardBank;
    private String cardSummary1;
    private String cardSummary2;
    private String cardSummary3;
    private String cardImage;
}
