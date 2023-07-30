package com.modu.app.prj.user.service;

import java.util.Map;

public interface OAuth2UserInfo {
	Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getid();
    String getnm();
}
