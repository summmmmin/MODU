package com.modu.app.prj.user.etc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.modu.app.prj.user.service.UserVO;



public class CustomSuccessHandler implements AuthenticationSuccessHandler{
   
   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
         Authentication authentication) throws IOException, ServletException {
      System.out.println("@@ CustomSuccessHandler실행 | | | 암호화 성공 @@");
      HttpSession session = request.getSession();
      UserVO vo = (UserVO) authentication.getPrincipal();
      System.out.println(vo.getNm());
      
      if(vo != null) {
    	  session.setAttribute("user", vo);
      }
      response.sendRedirect("/modu/prjList");
   }
}