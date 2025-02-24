package top.xmy.aiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import top.xmy.aiservice.Service.AIService;


@RestController
@RequestMapping("/ai")
public class AIServiceController {

    @Autowired
    private AIService aiService;

    @GetMapping("/ask")
    public String askQuestion(@RequestParam String question) {
        return aiService.callLanguageModel(question);
    }
}
