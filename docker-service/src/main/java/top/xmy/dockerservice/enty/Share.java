package top.xmy.dockerservice.enty;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_share")
public class Share {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String title;
    private Integer isOriginal;
    private String author;
    private String cover;
    private String summary;
    private Integer price;
    private String downloadUrl;
    private Integer buyCount;
    private Integer showFlag;
    private String auditStatus;
    private String reason;
    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time" ,fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
