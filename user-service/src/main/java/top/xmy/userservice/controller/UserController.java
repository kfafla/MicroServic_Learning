package top.xmy.userservice.controller;

import com.alibaba.nacos.api.model.v2.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import top.xmy.userservice.config.XmyProperties;
import top.xmy.userservice.entity.User;
import top.xmy.userservice.entity.UserVO;
import top.xmy.userservice.service.UserService;
@Slf4j
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {
    @GetMapping("/user")
    public String getUser(@RequestParam String username) {
        return "User:" + username;
    }
    @Autowired
    private UserService userService;
//在user-service 中，使用MP 访问数据库，提供/user/1这样的端点
    @Resource
   private XmyProperties xmyProperties;
//    @GetMapping("/{id}")
//    public UserVO etUserById(@PathVariable Integer id) {
//           return  userService.findById(id);
//    }
@GetMapping("/{id}")
public ResponseEntity<?> getUser(@PathVariable Integer id) {

    if (xmyProperties.getServiceFlag()) {
        UserVO user = userService.findById(id);
        return ResponseEntity.ok(user);
    } else {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("用户服务正在维护中，请稍后。。。");
    }
}

@PostMapping("/user/addBonus")
    public Result<String> addBonus(@RequestParam Integer userId,@RequestParam Integer bonus){
     userService.addBonus(userId,bonus);
     return Result.success("新增成功！");
}
}
