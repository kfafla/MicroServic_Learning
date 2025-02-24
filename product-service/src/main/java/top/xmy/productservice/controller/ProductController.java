package top.xmy.productservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/product")
    public String getProduct(@RequestParam String productId) {
        // 模拟返回产品信息
        return "产品ID：" + productId + "，产品名称: 产品A,价格：100.00";
    }
}
