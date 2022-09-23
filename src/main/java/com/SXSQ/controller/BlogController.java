package com.SXSQ.controller;

import com.SXSQ.bean.Response;
import com.SXSQ.mapper.BlogMapper;
import com.SXSQ.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @title: blogController
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/9/14 18:57
 **/

@RestController
@RequestMapping(value = "/SXSQ_FollowInsider/blog")
public class BlogController {

    @Resource
    BlogService blogService;

    @PostMapping(value = "/img")
    public Response saveImg(@RequestParam("file") MultipartFile multipartFile){
        /**
         * @param multipartFile
        * @description: TODO
        * @return: java.util.Map
        * @author: SXSQ
        * @date: 2022/9/14 19:06
        */

        return blogService.saveImg(multipartFile);
    }
}
