package com.spring_boot_mybatis.project.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring_boot_mybatis.project.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;

	@RequestMapping("/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@ResponseBody
	@RequestMapping("/login")
	public String loginCheck(@RequestParam HashMap<String, Object> param, HttpSession session) {
		String memId = service.loginCheck(param);
		String result = "fail";

		if (memId != null) {
			session.setAttribute("sid", memId);
			result = "success";
		}
		return result;
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 세션 무효화
		session.invalidate();
		return "redirect:/"; // index로 포워딩
	}
}
