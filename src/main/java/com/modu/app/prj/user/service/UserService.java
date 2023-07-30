package com.modu.app.prj.user.service;

import java.util.HashMap;
import java.util.Map;

public interface UserService {
	public int signup(UserVO userVO);
	public String idSearch(UserVO userVO);
	public int pwdSearch(UserVO userVO);
	public int idVaild(String id);	
	public String generateRandomToken();
	public UserVO emailAuth(String id);
	public int phNoVaild(String phNo);	
	public String updateEmailAuthStatus(String token);
	public String updateNm(Map<String, String> params);
	public String updatePwd(Map<String, String> params);
	public String updateId(Map<String, String> params);
	public String emailCode();
	public String updatePhone(Map<String, String> params);
	public int quitUser(String id);

}
