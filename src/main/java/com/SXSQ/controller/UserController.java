package com.SXSQ.controller;

import com.SXSQ.service.UserService;
import com.SXSQ.bean.Response;
import com.SXSQ.bean.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @title: UserController
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/9/7 21:09
 **/

@RestController
@RequestMapping(value = "/SXSQ_FollowInsider/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/test")
    public Response test(){
        return userService.test();
    }

    @PostMapping
    public Response login(@RequestBody User user){
        /**
         * @param user
         * @description: TODO
         * @return: com.SXSQ.bean.Response
         * @author: SXSQ
         * @date: 2022/9/9 21:53
         */

        return userService.login(user);
    }

    @PutMapping
    public Response register(@RequestBody User user){
        /**
         * @param user
         * @description: TODO
         * @return: com.SXSQ.pojo.Response
         * @author: SXSQ
         * @date: 2022/9/9 9:40
         */

        return userService.register(user);
    }

    @GetMapping("/verify/{temporary_id}")
    public Response verifyRegisterByEmail(@PathVariable("temporary_id") String temporary_id){
        /**
         * @param temporary_id
         * @description: TODO 邮箱验证，用户完成注册
         * @return: com.SXSQ.pojo.Response
         * @author: SXSQ
         * @date: 2022/9/9 9:44
         */

        return userService.verifyRegisterByEmail(temporary_id);
    }

    @GetMapping("/forgetPassword/username/{username}")
    public Response forgetPasswordByUsername(@PathVariable("username") String username){
        /**
         * @param username
        * @description: TODO
        * @return: com.SXSQ.bean.Response
        * @author: SXSQ
        * @date: 2022/9/16 10:51
        */

        return userService.forgetPasswordByUsername(username);
    }
}
