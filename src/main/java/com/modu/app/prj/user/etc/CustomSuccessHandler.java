package com.modu.app.prj.user.etc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.modu.app.prj.user.service.PrincipalDetails;
import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.user.service.UserVO;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	        Authentication authentication) throws IOException, ServletException {
	    Object principal = authentication.getPrincipal();

	    if (principal instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) principal;
	        
	        UserVO vo;
	        
	        if (userDetails instanceof PrincipalDetails) {
	            vo = ((PrincipalDetails) userDetails).getUserVO();
	        } else {

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

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired   
	PrjService prjService;
	
	   @Override
	   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	         Authentication authentication) throws IOException, ServletException {
		   
	       // 사용자 정보 가져오기
	       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	       String username = userDetails.getUsername();
	       System.out.println(username);
	       
	      System.out.println("@@ CustomSuccessHandler 실행 | 암호화 성공 @@");
	      
	      // 세션에 사용자 정보 저장
	      HttpSession session = request.getSession();
	      session.setAttribute("user", username);
	      
	      // 아이디 저장 체크 여부 확인
	      boolean rememberId = request.getParameter("rememberId") != null;
	      System.out.println(rememberId);
	      
	      if (rememberId) {
	    	// 아이디 저장을 위한 쿠키 생성
	    	  Cookie cookie = new Cookie("savedUsername", username);
	    	  cookie.setMaxAge(30 * 24 * 60 * 60); // 쿠키 수명 설정 (일단 30일)
	    	  cookie.setPath("/");
	    	  response.addCookie(cookie);
	          System.out.println("쿠키 : "  + cookie);
	      } else {
	          // 쿠키 삭제
	    	  Cookie cookie = new Cookie("savedUsername", null);
	    	  cookie.setMaxAge(0); // 쿠키 수명 삭제
	    	  cookie.setPath("/");
	    	  response.addCookie(cookie);

	      }
	    
	      UserVO vo = (UserVO) authentication.getPrincipal();
	      System.out.println(vo.getNm());
	      
	      if (vo != null) {
	          session.setAttribute("user", vo);
	          String inviteTk = (String) session.getAttribute("inviteTk");
	          if(inviteTk != null) {
	        	  PrjVO invite = prjService.selectInvite(inviteTk);
	        	  invite.setMembUniNo(vo.getMembUniNo());
				  invite.setNnm(vo.getNm());
				  invite.setPrjPubcId(vo.getId());
	        	  int result = prjService.insertPartiMemb(invite);
					if(result == 1) {
						System.out.println("초대 성공");
					}else if(result == 2) {
						System.out.println("초대 insert 오류");
					}else if(result == 3) {
						System.out.println("무료 플랜 초대 10명 초과");
					}else if(result == 4) {
						System.out.println("초대 오류");
					}

				  // session token 삭제
				  session.removeAttribute("inviteTk");
	          }else {
	        	  
	          }
	      }
	      
	      response.sendRedirect("/modu/prjList");
	   }
	}
}