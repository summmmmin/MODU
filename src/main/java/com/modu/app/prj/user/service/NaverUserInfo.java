package com.modu.app.prj.user.service;

import java.util.Map;
import java.util.UUID;

public class NaverUserInfo implements OAuth2UserInfo {
	 	private Map<String, Object> attributes; //OAuth2User.getAttributes();
	    private Map<String, Object> attributesResponse;

	    public NaverUserInfo(Map<String, Object> attributes) {
	        this.attributes = (Map<String, Object>) attributes.get("response");
	        this.attributesResponse = (Map<String, Object>) attributes.get("response");
	    }
	    
	    @Override
	    public Map<String, Object> getAttributes() {
	        return attributes;
	    }

	    @Override
	    public String getProviderId() {
	        return attributesResponse.get("id").toString().substring(0, 22);
	    }

	    @Override
	    public String getProvider() {
	        return "Naver";
	    }

	    @Override
	    public String getid() {
	        return attributesResponse.get("email").toString();
	    }

	    @Override
	    public String getnm() {
	        return attributesResponse.get("name").toString();
	    }

}
