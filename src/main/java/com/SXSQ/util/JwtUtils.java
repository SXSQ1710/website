package com.SXSQ.util;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @title: JwtUtils
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/9/8 16:15
 **/

public class JwtUtils {
//
//    // token时效：24小时
//    public static final long EXPIRE = 1000 * 60 * 60 * 24;
//    // 签名哈希的密钥，对于不同的加密算法来说含义不同
//    public static final String APP_SECRET = "F54EFHAfs34fWuSGs2jZWbYfwFE4H6KSE";
//
//    /**
//     * 根据用户id和昵称生成token
//     * @param username 用户昵称
//     * @return JWT规则生成的token
//     */
//    public static String getJwtToken(String username){
//        //随机生成用户临时id，在Redis使用
//        String id = UUID.randomUUID().toString();
//        //生成token
//        String JwtToken = Jwts.builder()
//                .setSubject("user")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
//                .claim("id", id)
//                .claim("username", username)
//                // 传入Key对象
//                .signWith(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
//                .compact();
//        return JwtToken;
//    }
//
//    /**
//     * 解析token
//     * @param jwtToken token字符串
//     * @return 返回Jws<Claims>对象
//     */
//    public static Jws<Claims> decode(String jwtToken) {
//        // 传入Key对象
//        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(jwtToken);
//        return claimsJws;
//    }
//
//    /**
//     * 判断token是否存在与有效
//     * @param jwtToken token字符串
//     * @return 如果token有效返回true，否则返回false
//     */
//    public static Jws<Claims> checkToken(String jwtToken) {
//        if(!StringUtils.hasLength(jwtToken)) return null;
//        Jws<Claims> decodeToken;
//        try {
//            decodeToken = decode(jwtToken);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return decodeToken;
//    }
//
//    /**
//     * 判断token是否存在与有效
//     * @param request Http请求对象
//     * @return 如果token有效返回true，否则返回false
//     */
//    public static Jws<Claims> checkToken(HttpServletRequest request) {
//        Jws<Claims> decodeToken;
//        try {
//            // 从http请求头中获取token字符串
//            String jwtToken = request.getHeader("token");
//            if(!StringUtils.hasLength(jwtToken)) return null;
//            decodeToken = decode(jwtToken);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return decodeToken;
//    }

}

