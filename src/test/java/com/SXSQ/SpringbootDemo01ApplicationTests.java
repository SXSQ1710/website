package com.SXSQ;

import com.SXSQ.mapper.UserMapper;
import com.SXSQ.util.EmailUtil;
import com.SXSQ.util.JwtUtils;
//import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class SpringbootDemo01ApplicationTests {

	@Autowired
	private UserMapper userDao;
	@Resource
	JavaMailSenderImpl mailSender;

	@Test
	void contextLoads() {
//		String id = UUID.randomUUID().toString();
//		System.out.println(id);
//		String jwtToken = JwtUtils.getJwtToken( "admin");
//		System.out.println(jwtToken);
//		Jws<Claims> decode = JwtUtils.checkToken(jwtToken);
//		String o;
//		if (decode != null) o = (String) decode.getBody().get("id");
//		else o = "token错误！";
//		System.out.println(o);
	}

	@Test
	public void testEmail() throws Exception{
		EmailUtil.sendMail("sxsq1710@163.com","验证码","123test123");
	}

}
