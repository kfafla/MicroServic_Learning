package top.xmy.contentservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xmy.contentservice.page.User;
import top.xmy.contentservice.page.UserVO;
import top.xmy.contentservice.result.Result;

@FeignClient(value = "user-service", fallbackFactory = UserServiceFallBackFactory.class)
public interface UserClient {
    @GetMapping(value = "/user/{id}")
    Result<User> getUserById(@PathVariable Integer id);

    @PostMapping("/user/user/addBonus")
    Result<String> addBonus(@RequestParam Integer userId, @RequestParam Integer bonus);
}