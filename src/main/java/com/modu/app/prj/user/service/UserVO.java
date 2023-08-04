package com.modu.app.prj.user.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO implements UserDetails {

	// 회원 고유번호
	private String membUniNo;

	// 회원 아이디
	private String id;

	// 회원 비밀번호
	private String pwd;

	// 회원 이름
	private String nm;

	// 회원 전화번호
	private String phNo;

	// SNS동의 여부
	private String sns;

	// 탈퇴여부
	private String quit;

	// 회원등급
	private String grd;

	// 회원가입 일자
	private Date regDt;

	// 이메일 인증 활성화 여부(링크클릭)
	private String emailAuth;

	// 아이디 고유 토큰값(이메일 인증에 필요)
	private String Token;

	// 소셜로그인 PROVIDER
	private String providerID;

	// 소셜로그인 로그인경로
	private String loginPath;
	
	   @Builder
	   public UserVO(String id, String nm, String pwd, String providerID) {
		  this.membUniNo = membUniNo;
	      this.id = id;
	      this.nm = nm;
	      this.pwd = pwd;
	      this.phNo = "정보없음";
	      this.emailAuth = "Y";
	      this.sns = "Y";
	      this.providerID = providerID;
	      this.grd = "N";
	   }

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

	public String getRole() {
		return grd;
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


	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String grd = getRole(); // Role이 ADMIN일 경우 ROLE_ADMIN 권한 부여
		if (grd != "" && grd != null) {
			if (grd.equals("A")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // 아닐경우 일반유저 권한 부여
		}
		System.out.println("권한 부여 : " + authorities);
		return authorities;
	}
}
