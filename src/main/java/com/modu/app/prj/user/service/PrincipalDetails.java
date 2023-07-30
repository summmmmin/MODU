package com.modu.app.prj.user.service;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;


import lombok.Getter;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {
	private UserVO userVO;
    private Map<String, Object> attributes;
    
    //UserDetails : Form 로그인 시 사용
    public PrincipalDetails(UserVO userVO) {
        this.userVO = userVO;
    }

    //OAuth2User : OAuth2 로그인 시 사용
    public PrincipalDetails(UserVO userVO, Map<String, Object> attributes) {
        //PrincipalOauth2UserService 참고
        this.userVO = userVO;
        this.attributes = attributes;
    }
    
    
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		if(userVO.getGrd().equals("Y")) {
			list.add(new SimpleGrantedAuthority("ROLE_REGULAR"));
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}else if(userVO.getGrd().equals("N")) {
			list.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	
		return list;
	}
	

	
	@Override
	public String getPassword() {
		return userVO.getPwd();
	}
	@Override
	public String getUsername() {
		return userVO.getId();
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


	 /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public String getName() {
        String sub = attributes.get("sub").toString();
        return sub;
    }

}
