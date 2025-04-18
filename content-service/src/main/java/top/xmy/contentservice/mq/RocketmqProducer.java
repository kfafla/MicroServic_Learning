package top.xmy.contentservice.mq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;
/**
 * 生产者服务类
 * @author xmy
 */
@Service
@Slf4j
public class RocketmqProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String msg) {
        rocketMQTemplate.syncSend(topic, msg);
        log.info("信息已发送：{}",msg);
    }
}
