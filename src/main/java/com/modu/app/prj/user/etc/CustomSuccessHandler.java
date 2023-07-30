package com.modu.app.prj.user.etc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.modu.app.prj.user.service.PrincipalDetails;
import com.modu.app.prj.user.service.UserVO;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	        Authentication authentication) throws IOException, ServletException {
	    Object principal = authentication.getPrincipal();

	    if (principal instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) principal;
	        
	        UserVO vo;
	        
	        // if userDetails is actually a PrincipalDetails instance, get UserVO from it
	        if (userDetails instanceof PrincipalDetails) {
	            vo = ((PrincipalDetails) userDetails).getUserVO();
	        } else {
	            // userDetails is not a PrincipalDetails instance, can't get UserVO
	            // handle this case appropriately
	            // for now, let's just print a message and return
	            System.out.println("UserDetails instance is not a PrincipalDetails instance, can't get UserVO.");
	            return;
	        }

	        System.out.println("@@ CustomSuccessHandler 실행 | 암호화 성공 @@");

	        // 세션에 사용자 정보 저장
	        HttpSession session = request.getSession();
	        session.setAttribute("user", vo);

	        // 아이디 저장 체크 여부 확인
	        boolean rememberId = request.getParameter("rememberId") != null;
	        System.out.println(rememberId);

	        if (rememberId) {
	            // 아이디 저장을 위한 쿠키 생성
	            Cookie cookie = new Cookie("savedUsername", vo.getId());
	            cookie.setMaxAge(30 * 24 * 60 * 60); // 쿠키 수명 설정 (일단 30일)
	            cookie.setPath("/");
	            response.addCookie(cookie);
	            System.out.println("쿠키 : " + cookie);
	        } else {
	            // 쿠키 삭제
	            Cookie cookie = new Cookie("savedUsername", null);
	            cookie.setMaxAge(0); // 쿠키 수명 삭제
	            cookie.setPath("/");
	            response.addCookie(cookie);
	        }
	    }

	    response.sendRedirect("/modu/prjList");
	}
}