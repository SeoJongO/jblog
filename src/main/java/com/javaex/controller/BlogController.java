package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blog(Model model, @PathVariable("id") String id) {
		System.out.println("BlogController.blog()");

		BlogVo blogVo = blogService.getBlog(id);

		model.addAttribute("blogVo", blogVo);

		return "/blog/blog-main";
	}

	@RequestMapping(value = "/{id}/admin/basic")
	public String adminBasic(HttpSession session, Model model, @PathVariable("id") String id) {
		System.out.println("BlogController.adminBasic()");

		BlogVo blogVo = blogService.getBlog(id);

		String authUserId = ((UserVo) session.getAttribute("authUser")).getId();

		if (authUserId == id) {

			model.addAttribute("blogVo", blogVo);

			return "/blog/admin/blog-admin-basic";

		} else {

			return "redirect:/" + id;
		}

	}

	// 블로그 기본설정 수정
	@RequestMapping(value = "/{id}/admin/basic/update")
	public String adminUpdate(@PathVariable("id") String id, @RequestParam("blogTitle") String blogTitle,
			@RequestParam("file") MultipartFile file) {
		System.out.println("BlogController.adminUpdate()");

		blogService.updateBlog(id, blogTitle, file);

		return "redirect:/" + id + "/admin/basic";
	}
}
