package com.modu.app.prj.user.service;

import java.sql.Date;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails{
//	MEMB_UNI_NO NOT NULL VARCHAR2(10) 
//	ID          NOT NULL VARCHAR2(30) 
//	PWD         NOT NULL VARCHAR2(32) 
//	NM          NOT NULL VARCHAR2(20) 
//	PH_NO                VARCHAR2(13) 
//	SNS         NOT NULL CHAR(1)      
//	QUIT        NOT NULL CHAR(1)      
//	GRD         NOT NULL CHAR(1)      
//	REG_DT      NOT NULL DATE  
	
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

	@Override
	public String getPassword() {
		return pwd;
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
