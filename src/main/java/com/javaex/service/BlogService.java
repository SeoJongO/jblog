package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	public BlogVo getBlog(String id) {
		System.out.println("BlogService.getBlog()");

		BlogVo blogVo = blogDao.getBlog(id);

		return blogVo;
	}

	public int updateBlog(String id, String blogTitle, MultipartFile file) {
		System.out.println("BlogService.updateBlog()");

		long fileSize = file.getSize();

		if (fileSize > 0) {

			String saveDir = "C:\\Users\\Hi-PC\\Desktop\\JavaStudy\\upload\\";

			String orgName = file.getOriginalFilename();
			System.out.println("orgName:" + orgName);

			String exName = orgName.substring(orgName.lastIndexOf("."));
			System.out.println("exName:" + exName);

			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			System.out.println("saveName:" + saveName);

			String filePath = saveDir + "\\" + saveName;
			System.out.println("filePath:" + filePath);

			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);

				bout.write(fileData);
				bout.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			BlogVo blogUpdateVo = new BlogVo(id, blogTitle, saveName);

			return blogDao.updateBlog(blogUpdateVo);

		} else {

			System.out.println(file);

			BlogVo blogUpdateVo = new BlogVo(id, blogTitle);

			return blogDao.updateBlogTitle(blogUpdateVo);

		}

	}
}
