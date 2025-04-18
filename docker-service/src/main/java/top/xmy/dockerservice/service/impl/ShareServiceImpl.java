package top.xmy.dockerservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.xmy.dockerservice.enty.Share;
import top.xmy.dockerservice.mapper.ShareMapper;
import top.xmy.dockerservice.service.ShareService;

@Service
public class ShareServiceImpl  extends ServiceImpl<ShareMapper, Share> implements ShareService {
}
