package com.SXSQ.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @title: R_User
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/9/12 16:45
 **/
@Data
@Builder
public class R_User implements Serializable {
    private static final long serialVersionUID = 206486233258930174L;

    private  String id;

    private String nickname;

    private String avatar;

    private String username;

    private String gender;

    private String email;

    private String role;

    private LocalDateTime createTime;
}
