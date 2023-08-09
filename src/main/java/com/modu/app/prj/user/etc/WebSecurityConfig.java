package com.modu.app.prj.user.etc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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
        http.csrf().disable()
        .authorizeRequests()
        	.antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/", "/signup/**", "/sms/**", "/modu/**", "/**", "/assets/**", "/FAQList/**", "/noticeList/**", "/subscribe/**").permitAll()
	        .anyRequest().authenticated()
        .and()
        .formLogin()
            .passwordParameter("pwd")
            .loginPage("/login").permitAll()
            .successHandler(authenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler())
            .failureUrl("/login?error=true") // 로그인 실패(비밀번호 틀림)
        .and()
        .logout((logout) -> logout.logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll())
        .oauth2Login()
            .loginPage("/login")
            .successHandler(authenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler())
            .userInfoEndpoint()
            .userService(principalOauth2UserService) // OAUTH2사용자는 이곳에서 처리
            
		;
		return http.build();
	}
}
