package top.xmy.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import top.xmy.userservice.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    void addBonus(int userId, int bonus);
}
