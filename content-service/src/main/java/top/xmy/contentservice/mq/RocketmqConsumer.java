package top.xmy.contentservice.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author ymx1205
 *
 */
@Service
@Slf4j
@RocketMQMessageListener(topic = "ymx-topic" , consumerGroup = "ymx-c-group")
public class RocketmqConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info("收到消息: {}",s);
    }


}
