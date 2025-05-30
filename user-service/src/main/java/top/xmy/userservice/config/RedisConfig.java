package top.xmy.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@RefreshScope
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisConfig {
    private String host;
    private int port;
    private String password;
}
