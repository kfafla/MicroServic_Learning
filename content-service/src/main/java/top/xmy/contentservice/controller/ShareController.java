package top.xmy.contentservice.controller;



import com.alibaba.csp.sentinel.annotation.SentinelResource;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xmy.contentservice.mapper.ShareMapper;
import top.xmy.contentservice.mq.BonusProducer;
import top.xmy.contentservice.mq.RocketmqProducer;
import top.xmy.contentservice.page.Share;
import top.xmy.contentservice.page.User;
import top.xmy.contentservice.client.UserClient;
import top.xmy.contentservice.page.UserVO;
import top.xmy.contentservice.result.Result;
import top.xmy.contentservice.service.ShareService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/share")
@Slf4j
public class ShareController {

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private RocketmqProducer rocketmqProducer;

    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    @SentinelResource(value = "/share/{id}")
    public Map<String, Object> getShareById(@PathVariable Integer id) {
//        try{
//            Thread.sleep(500);
//        }catch (InterruptedException e){
//            throw new RuntimeException(e);
//        }

        Share share = shareMapper.selectById(id);
        Result<User> userResult = userClient.getUserById(share.getUserId());
        User user = userResult.getData();

        Map<String, Object> result = new HashMap<>();
        result.put("share", share);
        result.put("user", user);
        return result;
    }


//    @Autowired
//    private ShareMapper shareMapper;
//
//    @Autowired
//    private UserClient userClient;
//
//    @GetMapping("/{id}")
//    public Map<String, Object> getShareById(@PathVariable Integer id) {
//        Share share = shareMapper.selectById(id);
//        User author = userClient.getUserById(share.getUserId());
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("share", share);
//        result.put("author", author);
//        return result;
//    }
    @GetMapping("/mq")
    public Result<String> sendMsg(){
        rocketmqProducer.sendMessage("ymx-topic","Hello, RocketMQ,这是来自ymx的消息");
        return Result.success("发送成功");
    }
    @Autowired
    private ShareService shareService;
    @Autowired
    private BonusProducer bonusProducer;
    //发布内容接口
   @PostMapping("/share/publish")
    public Result<String> publish(@RequestBody Share share) {
        shareService.publishShare(share);
        return Result.success("发布成功，等待审核!");
   }
   //审核内容接口
    @PostMapping("/share/approve/{id}")
    public Result<String> approveShare(@PathVariable Integer id) {
       shareService.approveShare(id);
       //发布小子，加积分50
        Share share = shareService.getById(id);
        bonusProducer.sendMessage(share.getUserId(),50);
        return Result.success("审核通过，积分奖励已发放");

    }
    @PostMapping("/check-in")
    public Result<String> checkIn(@RequestParam Integer userId) {
        try {
            shareService.dailyCheckIn(userId);
            return Result.success("签到成功，获得10积分！");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


}
