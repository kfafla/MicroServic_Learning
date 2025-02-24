package top.xmy.orderservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/order")
    public String createOrder(@RequestParam String username, @RequestParam String productId){
        String userserviceUrl = "http://localhost:8081/user?username=" + username;
        String userInfo = restTemplate.getForObject(userserviceUrl, String.class);
        String productUrl = "http://localhost:8083/product?productId=" + productId;
        String productInfo = restTemplate.getForObject(productUrl, String.class);
        return userInfo + ",订单产品信息是：" + productInfo;
    }
}
