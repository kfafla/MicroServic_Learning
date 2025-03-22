package top.xmy.musicservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliyunConfig {
    @Value("${aliyun.appkey}")
    private String appKey;

    @Value("${aliyun.access-token}")
    private String accessToken;

    public String getAppKey() {
        return appKey;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
