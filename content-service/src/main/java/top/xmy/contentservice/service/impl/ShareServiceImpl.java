package top.xmy.contentservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xmy.contentservice.mapper.BonusLogMapper;
import top.xmy.contentservice.mapper.ShareMapper;
import top.xmy.contentservice.mq.BonusProducer;
import top.xmy.contentservice.page.BonusLog;
import top.xmy.contentservice.page.Share;
import top.xmy.contentservice.service.ShareService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {
    private final ShareMapper shareMapper;
    private final BonusLogMapper bonusLogMapper; // 注入积分日志的 Mapper
    @Override
    public void publishShare(Share share) {
        share.setAuditStatus("NOT_YET");
        shareMapper.insert(share);
    }

    @Override
    public void approveShare(Integer shareId) {
        Share share = shareMapper.selectById(shareId);
        if (share != null) {
            share.setAuditStatus("PASS");
            share.setReason("审核通过");
            shareMapper.updateById(share);
        }
    }
    @Override
    public void dailyCheckIn(Integer userId) {
        // 检查用户是否已经签到
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayStart = now.toLocalDate().atStartOfDay();
        LocalDateTime todayEnd = todayStart.plusDays(1);

        Long count = bonusLogMapper.selectCount(
                new QueryWrapper<BonusLog>()
                        .eq("user_id", userId)
                        .eq("event", "每日签到")
                        .ge("create_time", todayStart)
                        .lt("create_time", todayEnd)
        );

        if (count != null && count > 0) {
            throw new RuntimeException("今日已签到，不能重复签到！");
        }

        // 签到成功，记录积分日志
        BonusLog bonusLog = new BonusLog();
        bonusLog.setUserId(userId);
        bonusLog.setBonus(10); // 签到加10分
        bonusLog.setEvent("每日签到");
        bonusLogMapper.insert(bonusLog);

        // 调用消息队列发送积分消息
        BonusProducer bonusProducer = new BonusProducer();
        bonusProducer.sendMessage(userId, 10);
    }
}
