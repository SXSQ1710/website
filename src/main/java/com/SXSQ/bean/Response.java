package com.SXSQ.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @title: Response
 * @Author SXSQ
 * @Description //TODO 返回统一格式数据的结果集
 * @Date 2022/9/7 21:17
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String code;
    private String msg;
    private Map<String, Object> data;

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Response success(ResponseStatus responseStatus){
        return new Response(responseStatus.getResponseStatus(),responseStatus.getDescription());
    }

    public static Response success(ResponseStatus responseStatus, Map<String, Object> data){
        return new Response(responseStatus.getResponseStatus(), responseStatus.getDescription(), data);
    }

    public static Response error(ResponseStatus responseStatus){
        return new Response(responseStatus.getResponseStatus(), responseStatus.getDescription());
    }

    public static Response error(ResponseStatus responseStatus, Map<String, Object> data){
        return new Response(responseStatus.getResponseStatus(), responseStatus.getDescription(), data);
    }
}


