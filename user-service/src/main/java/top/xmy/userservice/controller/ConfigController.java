package top.xmy.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xmy.userservice.config.DeepSeekConfig;
import top.xmy.userservice.config.OSSConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private OSSConfig ossConfig;

    @Autowired
    private DeepSeekConfig deepSeekConfig;

    @GetMapping("/database")
    public String getDatabaseConfig() {
        try (Connection connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL();
            String username = connection.getMetaData().getUserName();
            String result = String.format("Database URL: %s, Username: %s", url, username);

            // 将数据库配置写入数据库
            saveConfigToDatabase("database.url", url);
            saveConfigToDatabase("database.username", username);

            return result;
        } catch (Exception e) {
            return "Failed to get database config: " + e.getMessage();
        }
    }

    @GetMapping("/redis")
    public String getRedisConfig() {
        String result;
        if (redisConnectionFactory instanceof LettuceConnectionFactory) {
            LettuceConnectionFactory lettuceFactory = (LettuceConnectionFactory) redisConnectionFactory;
            result = String.format("Redis Host: %s, Port: %d", lettuceFactory.getHostName(), lettuceFactory.getPort());

            // 将 Redis 配置写入数据库
            saveConfigToDatabase("redis.host", lettuceFactory.getHostName());
            saveConfigToDatabase("redis.port", String.valueOf(lettuceFactory.getPort()));
        } else if (redisConnectionFactory instanceof JedisConnectionFactory) {
            JedisConnectionFactory jedisFactory = (JedisConnectionFactory) redisConnectionFactory;
            result = String.format("Redis Host: %s, Port: %d", jedisFactory.getHostName(), jedisFactory.getPort());

            // 将 Redis 配置写入数据库
            saveConfigToDatabase("redis.host", jedisFactory.getHostName());
            saveConfigToDatabase("redis.port", String.valueOf(jedisFactory.getPort()));
        } else {
            result = "Unknown Redis connection factory type";
        }
        return result;
    }

    @GetMapping("/oss")
    public String getOSSConfig() {
        String result = String.format("OSS Endpoint: %s, Bucket: %s", ossConfig.getEndpoint(), ossConfig.getBucketName());

        // 将 OSS 配置写入数据库
        saveConfigToDatabase("oss.endpoint", ossConfig.getEndpoint());
        saveConfigToDatabase("oss.bucket", ossConfig.getBucketName());

        return result;
    }

    @GetMapping("/deepseek")
    public String getDeepSeekConfig() {
        String result = "DeepSeek API Key: " + deepSeekConfig.getApikey();

        // 将 DeepSeek 配置写入数据库
        saveConfigToDatabase("deepseek.apikey", deepSeekConfig.getApikey());

        return result;
    }

    // 通用的数据库写入方法
    private void saveConfigToDatabase(String key, String value) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO config (`key`, `value`) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, key);
                statement.setString(2, value);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save config to database: " + e.getMessage(), e);
        }
    }
}
