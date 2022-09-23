package com.SXSQ.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * @title: Log
 * @Author SXSQ
 * @Description //TODO 日志实体
 * @Date 2022/9/12 16:45
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {
    private static final long serialVersionUID = 172895733695876052L;

    @TableId(type = IdType.ASSIGN_UUID,value = "id")
    private String id;
    
    private String userId;

    private String userToken;

    @NotBlank(message = "日志动作不能为空！")
    private String action;

    @TableField(fill = FieldFill.INSERT,value = "action_time")
    private LocalDateTime actionTime;


}

