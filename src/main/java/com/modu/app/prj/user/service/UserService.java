package com.modu.app.prj.user.service;

public interface UserService {
	public int signup(UserVO userVO);
	public String idSearch(UserVO userVO);
	public int pwdSearch(UserVO userVO);
	public int idVaild(String id);	
	public String generateRandomToken();
	public UserVO emailAuth(String id);
	public int phNoVaild(String phNo);	
	public String updateEmailAuthStatus(String token);
	public String updateNm(String id, String nm);
}
