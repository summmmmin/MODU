package com.modu.app.prj.user.service;

import java.sql.Date;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails{
	
	//회원 고유번호
	private String membUniNo;
	
	//회원 아이디
	private String id;
	
	//회원 비밀번호
	private String pwd;
	
	//회원 이름
	private String nm;
	
	//회원 전화번호
	private String phNo;
	
	//SNS동의 여부
	private String sns;
	
	//탈퇴여부
	private String quit;
	
	//회원등급
	private String grd;
	
	//회원가입 일자
	private Date regDt;
	
	//이메일 인증 활성화 여부(링크클릭)
	private String emailAuth;
	
	//아이디 고유 토큰값(이메일 인증에 필요)
	private String Token;
	
	//소셜로그인 PROVIDER
	private String providerID;
	
	// 소셜로그인 로그인경로
	private String loginPath;

	@Override
	public String getPassword() {
		return pwd;
	}
	
    public void setPassword(String password) {
        this.pwd = password;
    }


	@Override
	public String getUsername() {
		return id;
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

}
