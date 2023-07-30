package com.modu.app.prj.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.modu.app.prj.user.mapper.UserMapper;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	 
	@Autowired
	UserMapper userMapper;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		OAuth2User oAuth2User = super.loadUser(userRequest);

		OAuth2UserInfo oAuth2UserInfo = null;
		String provider = userRequest.getClientRegistration().getRegistrationId();
		String loginPath = "";

		if (provider.equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
			loginPath = "네이버";
		} else if (provider.equals("kakao")) {
			oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
			loginPath = "카카오";
		}
    
		String providerId = oAuth2UserInfo.getProviderId();

		String uuid = UUID.randomUUID().toString().substring(0, 6);		//소셜로그인은 랜덤비밀번호
		String password = scpwd.encode("패스워드" + uuid); 
		String name = oAuth2UserInfo.getnm();
		String email = oAuth2UserInfo.getid();

		UserVO byEmail = userMapper.idCheck(email);

		// DB에 없는 사용자라면 회원가입처리
		if (byEmail == null) {
		    byEmail = new UserVO();
		    byEmail.setId(email);
		    byEmail.setNm(name);
		    byEmail.setPwd(password);
		    byEmail.setPhNo("정보없음");
		    byEmail.setSns(loginPath);
		    byEmail.setProviderID(providerId);
		    byEmail.setGrd("N");
		    userMapper.signup(byEmail);
		}

		return new PrincipalDetails(byEmail, oAuth2User.getAttributes());
	}

}
