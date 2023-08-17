package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.service.GptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GptController {

    private final GptService gptService;

    @Autowired
    public GptController(GptService gptService) {
        this.gptService = gptService;
    }

    @GetMapping("/ezbot")
    public String chatPage() {
        return "chat/ezbot"; // resources/templates/ask.html을 렌더링
    }

    @PostMapping("/ezbot")
    @ResponseBody
    public Map<String, String> ask(@RequestBody String prompt) {
        Map<String, String> responseMap = new HashMap<>();
        String answer = gptService.ezbot(prompt);
        responseMap.put("answer", answer);
        return responseMap;
    }
}



