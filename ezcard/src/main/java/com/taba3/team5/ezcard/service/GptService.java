package com.taba3.team5.ezcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GptService {
    @Autowired
    private final OpenAIApi openAIApi;

    @Autowired
    public GptService(OpenAIApi openAIApi) {
        this.openAIApi = openAIApi;
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
