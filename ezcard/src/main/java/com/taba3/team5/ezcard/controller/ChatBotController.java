package com.taba3.team5.ezcard.controller;

import com.taba3.team5.ezcard.dto.chatbot.ChatBotResponse;
import com.taba3.team5.ezcard.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatBotController {

    private final ChatBotService chatBotService;

    @Autowired
    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @PostMapping(path = "/ezbot", produces = "application/json")
    public ResponseEntity<ChatBotResponse> ezbot(@RequestBody String userinput) {
        ChatBotResponse chatBotResponse = chatBotService.ezbot(userinput);

        if (chatBotResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(chatBotResponse);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
