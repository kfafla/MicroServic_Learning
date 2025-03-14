package top.xmy.contentservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.xmy.contentservice.page.User;
import top.xmy.contentservice.result.Result;

@FeignClient(value = "user-service", fallbackFactory = UserServiceFallBackFactory.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    Result<User> getUserById(@PathVariable Integer id);
}
