package com.modu.app.prj.user.service;

import java.util.Map;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OAuth2UserRequest {
	private final ClientRegistration clientRegistration;// 인증서버의 정보를 가져온다
	private final OAuth2AccessToken accessToken; // 인증 토큰값을 가져온다
	private final Map<String, Object> additionalParameters; // 유저의 정보를 가져온다
}
