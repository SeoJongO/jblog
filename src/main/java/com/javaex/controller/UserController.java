package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserContoller.loginForm()");

		return "user/loginForm";
	}

	@ResponseBody
	@RequestMapping(value = "/idCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean idCheck(@RequestParam("id") String id) {
		System.out.println("UserController.idCheck()");

		boolean state = userService.getUser(id);

		return state;
	}

	@RequestMapping(value = "/user/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpSession session, @ModelAttribute UserVo userVo) {
		System.out.println("UserContoller.login()");

		UserVo authUser = userService.getUser(userVo);

		System.out.println(authUser);

		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else {
			return "redirect:/user/loginForm?result=fail";
		}

	}

	@RequestMapping(value = "/user/logout")
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");

		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	@RequestMapping(value = "/user/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserContoller.joinForm()");

		return "user/joinForm";
	}

	@RequestMapping(value = "/user/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserContoller.join()");

		int count = userService.joinUser(userVo);

		if (count > 0) {
			return "user/joinSuccess";
		} else {
			return "/";
		}

	}
}
