package top.xmy.contentservice.controller;



import com.alibaba.csp.sentinel.annotation.SentinelResource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xmy.contentservice.mapper.ShareMapper;
import top.xmy.contentservice.page.Share;
import top.xmy.contentservice.page.User;
import top.xmy.contentservice.client.UserClient;
import top.xmy.contentservice.result.Result;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ShareMapper shareMapper;

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
        User author = userResult.getData();

        Map<String, Object> result = new HashMap<>();
        result.put("share", share);
        result.put("author", author);
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
}
