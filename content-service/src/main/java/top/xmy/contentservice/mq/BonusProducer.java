package top.xmy.contentservice.mq;

import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

@Service
public class BonusProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    public void sendMessage(Integer userId, Integer bonus) {
        rocketMQTemplate.convertAndSend("BonusTopic_ymx",userId + ":" + bonus);
    }

}
