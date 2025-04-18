package top.xmy.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xmy.userservice.entity.User;
import top.xmy.userservice.entity.UserVO;
import top.xmy.userservice.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserVO findById(Integer id) {
        User user = userMapper.selectById(id);
        return new UserVO(user.getId(), user.getUserName(), user.getAvatarUrl(),user.getBonus());
    }

    public void addBonus(int userId,int bonus) {
        User user = userMapper.selectById(userId);
        if(user!= null) {
            user.setBonus(user.getBonus() + bonus);
            userMapper.updateById(user);
        }
    }
}
