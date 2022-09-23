package com.SXSQ.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @title: User
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/8/8 11:29
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 249484147665770015L;

    @TableId(type = IdType.ASSIGN_UUID,value = "id")
    private  String id;

    private String nickname;

    private String avatar;

    @NotBlank(message = "用户名不能为空")
    private String username;

    private String password;

    private String gender;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private String role;

    @TableField(fill = FieldFill.INSERT,value = "create_time")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE,value = "update_time")
    private LocalDateTime updateTime;

    @Version
    private int version;
}
