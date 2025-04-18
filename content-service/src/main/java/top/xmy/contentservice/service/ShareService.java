package top.xmy.contentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.xmy.contentservice.page.Share;

public interface ShareService  extends IService<Share> {

    /**
     * 发布内容
     *
     * @param share
     */

    void publishShare(Share share);

    /**
     * 审核内容
     *
     * @param shareId
     */
    void approveShare(Integer shareId);

    /**
     * 每日签到
     * @param userId
     */
    void dailyCheckIn(Integer userId);
}
