package top.xmy.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.context.annotation.Configuration;



@Configuration
@Data
@RefreshScope
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseConfig {
    private String url;
    private String username;
    private String password;
}
