package com.modu.app.prj.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.login.service.LoginService;
import com.modu.app.prj.login.service.LoginVO;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	// 로그인 기능
	@GetMapping("login")
	public String login(LoginVO vo, HttpServletRequest request) {
		LoginVO login = loginService.login(vo);
		System.out.println(login);
		
		HttpSession session = request.getSession();
		if (login != null) {
			session.setAttribute("user", login);
			session.setAttribute("userid", login.getNm());
			return "index";
		} else {
			return "Login/login";
		}
	}

	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // 세션 지우는거
		}
		return "redirect:/login";
	}

	// 회원가입 페이지 이동
//	@GetMapping("/member")
//	public String member() {
//		return "board/member";
//	}
//
//	// 회원가입
//	@PostMapping("/member")
//	public String member(LoginVO vo, Model model) {
//		LoginVO member = loginService.login(vo);
//		model.addAttribute("member", member);
//		return "redirect:/login";
//	}
//
//	// 중복확인
//	@RequestMapping("/duplication")
//	@ResponseBody
//	public String duplication(@RequestBody String id) {
//		LoginVO vo = new LoginVO();
//		System.out.println("123123" + id);
//		vo.setId(id);
//		LoginVO lvo = loginService.login(vo);
//		System.out.println(lvo);
//		if (lvo == null) {
//			return "OK";
//		} else {
//			return "Fail";
//		}
//	}
}