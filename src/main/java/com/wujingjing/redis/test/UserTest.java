package com.wujingjing.redis.test;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.wujingjing.redis.util.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")


public class UserTest {
	@Autowired
	RedisTemplate redisTemplate;
	
	@Test
	public void save() {
		long start = System.currentTimeMillis();
		for (int i = 0; i <10000 ; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			
			User user = new User();
			user.setId(i);
			user.setName(UserUtils.getName());
			user.setBirthday(UserUtils.getBirthday());
			user.setMail(UserUtils.getMail());
			user.setPhone(UserUtils.getPhone());
			user.setSex(UserUtils.getSex());
			map.put("key"+ i,JSON.toJSONString(user));
			redisTemplate.opsForHash().putAll("user"+i,map);
			System.out.println(user);
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式是：HASH");
		System.out.println("保存数量是：10w个对象");
		System.out.println("序列化时间是："+(end-start));
	
	}
	
	
	

}
