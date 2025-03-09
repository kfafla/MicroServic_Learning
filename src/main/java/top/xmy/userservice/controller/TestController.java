package top.xmy.userservice.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xmy.userservice.config.XmyProperties;

@RefreshScope
@RestController
public class TestController {
//    @Value("${xmy.username}")
//    private String username;
//
//    @Value("${xmy.job}")
//    private String job;
 @Resource
 private XmyProperties xmyProperties;
    @GetMapping("/test")
      public String get() {
        return "读取到配置文件中的用户名：" + xmyProperties.getUsername() + "，职业为：" + xmyProperties.getJob();
    }
}
