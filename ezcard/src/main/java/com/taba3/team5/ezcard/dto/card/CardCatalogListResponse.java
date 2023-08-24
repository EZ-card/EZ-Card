package com.taba3.team5.ezcard.dto.card;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Data
public class CardCatalogListResponse {
    private List<CardCatalogDto> cardCatalogDtoList;

    public void setCardCatalogList(List<CardCatalogDto> cardCatalogDtoList) {
        this.cardCatalogDtoList = cardCatalogDtoList;

    }
}

