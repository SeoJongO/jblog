package com.javaex.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo getUser(UserVo userVo) {
		System.out.println("UserDao.getUser()");
		
		return sqlSession.selectOne("users.getUser", userVo);
	}
	
	public UserVo getUser(String id) {
		System.out.println("UserDao.selectUser()");

		return sqlSession.selectOne("users.getUserById", id);
	}
	
	public int joinUser(UserVo userVo) {
		System.out.println("UserDao.joinUser()");
		
		return sqlSession.insert("users.joinUser", userVo);
	}
	
	public int createBlog(Map<String, Object> map) {
		System.out.println("UserDao.createBlog()");
		
		return sqlSession.insert("blog.createBlog", map);
	}
}
