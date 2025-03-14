package top.xmy.requestservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController2 {
    @Resource
    private RestTemplate restTemplate;
    private final String SERVER_URL = "http://localhost:8080";
    @GetMapping("/restTemplateTest1")
    public String restTemplateTest() {
       return  restTemplate.getForObject(SERVER_URL + "/hello", String.class);
    }
}
