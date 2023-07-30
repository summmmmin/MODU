package com.modu.app.prj.user.etc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import com.modu.app.prj.user.service.PrincipalOauth2UserService;
import com.modu.app.prj.user.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	UserService userService;
	@Autowired
	PrincipalOauth2UserService principalOauth2UserService;

	private static final ClearSiteDataHeaderWriter.Directive[] SOURCE = { ClearSiteDataHeaderWriter.Directive.CACHE,
			ClearSiteDataHeaderWriter.Directive.COOKIES, ClearSiteDataHeaderWriter.Directive.STORAGE,
			ClearSiteDataHeaderWriter.Directive.EXECUTION_CONTEXTS };

	@Bean
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
		http.csrf().disable().authorizeHttpRequests()
				.antMatchers("/", "/static/**", "/signup/**", "/sms/**", "/modu/**", "/**").permitAll().anyRequest()
				.authenticated().and().formLogin().passwordParameter("pwd")
				.successHandler(authenticationSuccessHandler()).failureHandler(authenticationFailureHandler())
				.loginPage("/login").permitAll().and() 
				.logout((logout) -> logout.logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll())
				.oauth2Login() // OAuth2기반의 로그인인 경우
				.loginPage("/login") // 인증이 필요한 URL에 접근하면 /loginForm으로 이동
				.successHandler(authenticationSuccessHandler()) // 로그인 성공하면 "/" 으로 이동
				.failureHandler(authenticationFailureHandler()) // 로그인 실패 시 /loginForm으로 이동
				.userInfoEndpoint() // 로그인 성공 후 사용자정보를 가져온다
				.userService(principalOauth2UserService) // 사용자정보를 처리할 때 사용한다

		;

		return http.build();
	}
}
