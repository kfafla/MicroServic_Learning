package top.xmy.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.xmy.userservice.entity.UserVO;
import top.xmy.userservice.service.UserService;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/user")
    public String getUser(@RequestParam String username) {
        return "User:" + username;
    }
    @Autowired
    private UserService userService;
//在user-service 中，使用MP 访问数据库，提供/user/1这样的端点
    @GetMapping("/{id}")
    public UserVO getUserById(@PathVariable Integer id) {
        log.info("用户服务被调用！");
        return userService.findById(id);
    }
}
