package com.modu.app.prj.user.mapper;

import com.modu.app.prj.user.service.UserVO;

public interface UserMapper {
	public UserVO loginCheck(String id);
	public int signup(UserVO userVO);
	public UserVO idSearch(UserVO userVO);
	public int pwdSearch(UserVO userVO);
	public int idVaild(String id);
	public UserVO emailAuth(String id);
	public int phNoVaild(String phNo);
	public int updateEmailAuthStatus(String token);
	public int updateNm(String id, String nm);
	
}
