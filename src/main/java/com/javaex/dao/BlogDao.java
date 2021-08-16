package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;

	public BlogVo getBlog(String id) {
		System.out.println("BlogDao.getBlog()");

		return sqlSession.selectOne("blog.getBlog", id);
	}

	public int updateBlog(BlogVo blogVo) {
		System.out.println("BlogDao.updateBlog()");

		return sqlSession.update("blog.updateBlog", blogVo);
	}

	public int updateBlogTitle(BlogVo blogVo) {
		System.out.println("BlogDao.updateBlogTitle()");

		return sqlSession.update("blog.updateBlogTitle", blogVo);
	}
}
