package top.xmy.requestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class WebClientController {
    private final String SERVICE_NAME = "https://www.wanandroid.com";
    private final WebClient webClient = WebClient.builder()
            .baseUrl(SERVICE_NAME)
            .build();

    @GetMapping("/webClientTest")
    public String webClientTest() {
        // 使用 WebClient 调用接口并返回 JSON 数据
        Mono<String> responseMono = webClient.get()
                .uri("/article/list/{page}/json",200)
                .retrieve()
                .bodyToMono(String.class);

        // 阻塞等待响应并返回 JSON 数据
        return responseMono.block();
    }
}
