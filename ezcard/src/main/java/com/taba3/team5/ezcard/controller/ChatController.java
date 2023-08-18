package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.service.GptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChatController {

    private final GptService gptService;

    @Autowired
    public ChatController(GptService gptService) {
        this.gptService = gptService;
    }

    @PostMapping("/ezbot")
    @ResponseBody
    public Map<String, String> chat(@RequestBody String prompt) {

        Map<String , String> responseMap = new HashMap<>();
        String answer = gptService.ezbot(prompt);
        responseMap.put("gpt", answer);
        return responseMap;
    }
}



//public Map<String, String> chat(@RequestBody String prompt) {
//
//        Map<String , String> responseMap = new HashMap<>();
//        String answer = gptService.ezbot(prompt);
//        responseMap.put("gpt", answer);
//        return responseMap;
//    }