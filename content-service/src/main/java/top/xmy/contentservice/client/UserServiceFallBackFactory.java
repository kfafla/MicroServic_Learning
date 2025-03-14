package top.xmy.contentservice.client;



import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import top.xmy.contentservice.page.User;
import top.xmy.contentservice.result.Result;

@Component
@Slf4j
public class UserServiceFallBackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        log.error("用户服务调用异常:", cause);
        return new UserClient() {
            @Override
            public Result<User> getUserById(Integer id) {
                User user = new User();
                user.setId(-1);
                user.setUserName("默认用户");
                user.setAvatarUrl("https://www.baidu.com/img/flexible/logo/pc/result.png");
                return Result.success(user);
            }
        };
    }
}