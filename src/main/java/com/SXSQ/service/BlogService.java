package com.SXSQ.service;

import com.SXSQ.bean.Response;
import com.SXSQ.bean.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @title: blogService
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/9/14 18:56
 **/
@Slf4j
@Service
public class BlogService {

    @Value("${myConfig.filePath}")
    private String savePath;

    public Response saveImg (MultipartFile multipartFile){
        /**
         * @param multipartFile
        * @description: TODO
        * @return: com.SXSQ.bean.Response
        * @author: SXSQ
        * @date: 2022/9/15 15:34
        */

        try {
            Response response = f_saveImg(savePath, multipartFile);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(ResponseStatus.ERROR);
        }

    }

    public static Response f_saveImg(String savePath,MultipartFile multipartFile) throws Exception{
        /**
         * @param savePath
         * @param multipartFile
        * @description: TODO 保存图片工具类
        * @return: com.SXSQ.bean.Response
        * @author: SXSQ
        * @date: 2022/9/15 15:34
        */

        if (!multipartFile.isEmpty()){
            // 上传文件/图像到指定文件夹（这里可以改成你想存放地址的相对路径）
            File savePos = new File(savePath);
            if(!savePos.exists()){  // 不存在，则创建该文件夹
                savePos.mkdir();
            }
            // 获取存放位置的规范路径
            String realPath = savePos.getCanonicalPath();
            //获取源照片名
            String originalFilename = multipartFile.getOriginalFilename();
            //获取照片后缀名
            String suffixName=originalFilename.substring(originalFilename.lastIndexOf('.'));
            // 上传该文件/图像至该文件夹下
            String returnImSrc ="/images/" + UUID.randomUUID().toString()+suffixName;
            multipartFile.transferTo(new File( realPath + returnImSrc));
            Map<String,Object> data = new HashMap();
            data.put("imgSrc",returnImSrc);
            return Response.success(ResponseStatus.SUCCESS,data);
        }else {
            return Response.error(ResponseStatus.ERROR);
        }

    }
}
