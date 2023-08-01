package com.modu.app.prj.user.etc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
		registry.addViewController("/login").setViewName("Login/login");
		// URL주소가 main으로 지정  || 로그인 성공시 넘어갈 페이지
		//registry.addViewController("/main").setViewName("index");
		
		
	}
}