package com.modu.app.prj.user.etc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
		@Bean   //== @Component
	   PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	   }
	   
	   @Bean
	   CustomFailureHandler authenticationFailureHandler() {
	      return new CustomFailureHandler();
	   }
	   
	   @Bean
	   CustomSuccessHandler authenticationSuccessHandler() {
	      return new CustomSuccessHandler();
	   }
	   
	   @Bean
	   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	       http
	           .csrf().disable()		//csrf공격방지
	           .authorizeHttpRequests()	//요청설정
	           .antMatchers("/", "/static/**", "/signup/**", "/sms/**", "/modu/**", "/**", "/admin/**").permitAll()
	           .anyRequest().authenticated()	// antMatchers 외의 기능은 권한요구(인증)
	           .and()
	           .formLogin()					// 로그인
	           .passwordParameter("pwd")	// 비밀번호 받아옴
	           .successHandler(authenticationSuccessHandler())	// 로그인 성공했을 경우
	           .failureHandler(authenticationFailureHandler())
	           .loginPage("/login")
	           .failureUrl("/login?error=true") // 로그인 실패(비밀번호 틀림)
	           .permitAll()
	           .and()
	           .logout((logout) -> logout.permitAll());

	       return http.build();
	   }

}