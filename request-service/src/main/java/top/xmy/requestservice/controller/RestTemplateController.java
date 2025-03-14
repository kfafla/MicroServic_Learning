package top.xmy.requestservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

public class RestTemplateController {
    @Resource
    private RestTemplate restTemplate;
    private final String SERVER_URL = "https://www.wanandroid.com";
    @GetMapping("/restTemplateTest")
    public String restTemplateTest() {
        return  restTemplate.getForObject(SERVER_URL + "/banner/json", String.class);
    }
}
