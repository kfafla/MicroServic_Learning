package top.xmy.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xmy")
@Data
public class XmyProperties {
    private String username;
    private String job;
    private Boolean serviceFlag;
}
