package com.SXSQ.mapper;

import com.SXSQ.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @title: UserDao
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/9/5 21:56
 **/

@Repository
public interface UserMapper extends BaseMapper<User> {
}
