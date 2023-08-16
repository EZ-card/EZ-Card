package com.taba3.team5.ezcard.card.controller;

import com.taba3.team5.ezcard.card.dto.CardDTO;
import com.taba3.team5.ezcard.card.service.CardService;
import com.taba3.team5.ezcard.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
