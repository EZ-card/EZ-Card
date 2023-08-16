package com.taba3.team5.ezcard.ezbot.service;

import org.springframework.stereotype.Service;

@Service
public class GptService {
    private final  OpenAIApi openAIApi;

    public GptService(){
        this.openAIApi = new OpenAIApi();
    }

    public String ezbot(String prompt) {
        try {
            return openAIApi.ezbot(prompt);
        } catch (Exception e) {
            e.printStackTrace();
            return "API 호출 중 오류가 발생했습니다: " + e.getMessage();
        }
    }
}
