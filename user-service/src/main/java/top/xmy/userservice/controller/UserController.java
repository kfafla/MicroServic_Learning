package top.xmy.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.xmy.userservice.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/user")
    public String getUser(@RequestParam String username) {
        return "User:" + username;
    }
    @Autowired
    private UserService userService;

    @GetMapping("/ask-ai")
    public String askAI(@RequestParam String question) {
        return userService.askAI(question);
    }
}
