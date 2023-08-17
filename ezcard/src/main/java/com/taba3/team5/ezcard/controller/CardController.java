package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.dto.CardDTO;
import com.taba3.team5.ezcard.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    // 1. 카드 목록
    @GetMapping("/card/list")
    public String findAll(Model model) {
        List<CardDTO> cardDTOList = cardService.findAll();
        model.addAttribute("cardList", cardDTOList);
        return "card/list";
    }

    @GetMapping("/card/id/{id}")
    public String findById(@PathVariable Long id, Model model) {
        CardDTO cardDTO = cardService.findById(id);
        model.addAttribute("card", cardDTO);
        return "card/detail";
    }


}
