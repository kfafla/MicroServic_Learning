package top.xmy.aiservice.controller;

import org.springframework.web.bind.annotation.*;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    private final top.xmy.aiservice.client.AliyunOcrClient ocrClient;

    public OcrController(top.xmy.aiservice.client.AliyunOcrClient ocrClient) {
        this.ocrClient = ocrClient;
    }

    @GetMapping("/recognizeByUrl")
    public String recognizeTextByUrl(@RequestParam("url") String imageUrl) {
        try {
            return ocrClient.recognizeTextByUrl(imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}