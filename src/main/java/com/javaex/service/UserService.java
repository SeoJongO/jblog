package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public UserVo getUser(UserVo userVo) {
		System.out.println("UserService.getUser()");
		
		return userDao.getUser(userVo);
	}
	
	public boolean getUser(String id) {
		System.out.println("[UserService.getUser()]");
		
		UserVo userVo = userDao.getUser(id);
		
		if(userVo == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public int joinUser(UserVo userVo) {
		System.out.println("UserService.joinUser()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", userVo.getId());
		map.put("blogTitle", userVo.getUserName()+"의 블로그입니다");
		
		userDao.joinUser(userVo);
		
		return userDao.createBlog(map);
	}
	
}
