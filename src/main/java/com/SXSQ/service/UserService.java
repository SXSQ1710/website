package com.SXSQ.service;

import cn.dev33.satoken.secure.SaBase64Util;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.SXSQ.bean.R_User;
import com.SXSQ.bean.Response;
import com.SXSQ.bean.ResponseStatus;
import com.SXSQ.bean.User;
import com.SXSQ.mapper.UserMapper;
import com.SXSQ.util.EmailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @title: UserService
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/9/10 21:49
 **/
@Service
public class UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    RedisTemplate<String, User> redisTemplate;
    @Value("${myConfig.serveUrl}")
    private String serveUrl;

    public Response test(){
        String username =(String) StpUtil.getSession().get("username");
        System.out.println(username);
        return Response.success(ResponseStatus.SUCCESS);
    }

    public Response login(User user){
        /**
         * @param user
        * @description: TODO 用户登录
        * @return: com.SXSQ.bean.Response
        * @author: SXSQ
        * @date: 2022/9/9 21:53
        */

        Map<String,Object> data = new HashMap();
        String username = user.getUsername();
        String password = SaBase64Util.encode(user.getPassword());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        User userInfo = userMapper.selectOne(wrapper);
        if (userInfo != null) {
            //jwtToken = JwtUtils.getJwtToken(username);
            //会话登录
            StpUtil.login(userInfo.getId());
            //返回当前会话的 token 详细参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            data.put("tokenName",tokenInfo.tokenName);
            data.put("tokenValue",tokenInfo.tokenValue);
            data.put("userInfo",
                    R_User.builder().id(userInfo.getId())
                    .nickname(userInfo.getNickname())
                    .avatar(userInfo.getAvatar())
                    .username(userInfo.getUsername())
                    .gender(userInfo.getGender())
                    .email(userInfo.getEmail())
                    .role(userInfo.getRole())
                    .createTime(userInfo.getCreateTime()).build());

            SaSession session = StpUtil.getSession();
            session.set("userID",userInfo.getId());

            return Response.success(ResponseStatus.USER_LOGIN_SUCCESS, data);

        }else {
            return Response.error(ResponseStatus.USER_LOGIN_FAIL);

        }
    }


    public Response register(User user){
        /**
         * @param user
         * @description: TODO 用户提交注册账号申请，发送邮件进行验证
         * @return: com.SXSQ.pojo.Response
         * @author: SXSQ
         * @date: 2022/9/9 9:40
         */

        try {
            Map<String,Object> map = new HashMap();
            map.put("username",user.getUsername());
            List<User> users = userMapper.selectByMap(map);
            if (users.size() == 0){
                //密码加密、昵称默认用户名、头像默认
                user.setPassword(SaBase64Util.encode(user.getPassword()));
                user.setNickname(user.getUsername());
                user.setAvatar("/user_avatar/defaultAvatar.jpeg");
                //生成临时用户id
                String temporary_id = UUID.randomUUID().toString();
                System.out.println("用户注册申请："+user.toString());
                //将用户信息存入redis，有效时间5分钟，等待用户通过邮箱完成注册
                redisTemplate.opsForHash().put("temporary_id",temporary_id,user);
                redisTemplate.expire(temporary_id,300, TimeUnit.SECONDS);
                //redisTemplate.opsForValue().set(temporary_id,user,300, TimeUnit.SECONDS);
                //redisTemplate.expire(temporary_id,300, TimeUnit.SECONDS);
                //发送邮件
                String text = "<html>\n" +
                        "<body>\n" +
                        "<p>请点击下方链接注册</p>\n" +
                        "<a href=\""+ serveUrl +"/SXSQ_FollowInsider/user/verify/"+temporary_id+
                        "\">"+ serveUrl +"/SXSQ_FollowInsider/user/verify/"+temporary_id+"</a>" +
                        "</body>\n" +
                        "</html>";
                EmailUtil.sendMail(user.getEmail(),"欢迎注册博客网站！",text);
            }else {
                return Response.error(ResponseStatus.USER_REGISTER_REQUEST_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(ResponseStatus.USER_REGISTER_REQUEST_ERROR);

        }
        return Response.success(ResponseStatus.USER_REGISTER_REQUEST_SUCCESS);

    }


    public Response verifyRegisterByEmail(String temporary_id){
        /**
         * @param temporary_id
        * @description: TODO 用户通过邮箱完成二次验证
        * @return: com.SXSQ.bean.Response
        * @author: SXSQ
        * @date: 2022/9/12 15:56
        */
        User user =(User)redisTemplate.opsForHash().get("temporary_id", temporary_id);

        if (user != null){
            //内存中存在临时id的话，将临时id对应的用户信息写入数据库
            user.setId(temporary_id);
            user.setRole("游客");
            System.out.println(user);
            userMapper.insert(user);
            System.out.println("添加用户："+user.toString());
            //成功将用户信息写入数据库后，将内存中的响应用户信息进行删除
            redisTemplate.opsForHash().delete("temporary_id",temporary_id);
            return Response.success(ResponseStatus.USER_REGISTER_SUCCESS);

        }else {
            return Response.error(ResponseStatus.USER_REGISTER_FAIL);

        }

    }

    public Response forgetPasswordByUsername(String username){
        /**
         * @param username
        * @description: TODO
        * @return: com.SXSQ.bean.Response
        * @author: SXSQ
        * @date: 2022/9/16 14:57
        */

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if ( user!=null ){
            try {
                redisTemplate.opsForHash().put("forgetPassword",user.getId(),user);
                String text = "<html>\n" +
                        "<body>\n" +

                        "</body>\n" +
                        "</html>";
                EmailUtil.sendMail(user.getEmail(),"密码修改",text);
                return Response.success(ResponseStatus.USER_FORGET_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error(ResponseStatus.USER_FORGET_ERROR);
            }
        }else {
            return Response.error(ResponseStatus.USER_FORGET_FAIL);
        }
    }
}
