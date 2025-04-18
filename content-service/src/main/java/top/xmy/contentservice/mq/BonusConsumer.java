package top.xmy.contentservice.mq;

import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
import top.xmy.contentservice.client.UserClient;
import top.xmy.contentservice.mapper.BonusLogMapper;
import top.xmy.contentservice.page.BonusLog;

@Service
@RocketMQMessageListener(topic = "BonusTopic_ymx",consumerGroup = "ymx-c-group")
@AllArgsConstructor
public class BonusConsumer implements RocketMQListener<String> {
    private final UserClient userClient;
    private final BonusLogMapper bonusLogMapper;

    @Override
    public void onMessage(String s) {
        String[] split = s.split(":");
        Integer userId = Integer.valueOf(split[0]);
        Integer bonus = Integer.valueOf(split[1]);
        //1.给用户加积分
        userClient.addBonus(userId, bonus);
        //2.记录积分日志
        BonusLog bonusLog = new BonusLog();
        bonusLog.setUserId(userId);
        bonusLog.setBonus(bonus);
        bonusLog.setEvent("投稿加积分");
        bonusLogMapper.insert(bonusLog);
    }
}
