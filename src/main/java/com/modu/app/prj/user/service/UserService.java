package com.modu.app.prj.user.service;

public interface UserService {
	public int signup(UserVO userVO);
	public int idSearch(UserVO userVO);
	public int pwdSearch(UserVO userVO);
	public int idVaild(String id);	
	public String generateRandomToken();
	public UserVO emailAuth(String id);
		
}
