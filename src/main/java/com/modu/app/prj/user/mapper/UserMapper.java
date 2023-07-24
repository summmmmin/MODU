package com.modu.app.prj.user.mapper;

import com.modu.app.prj.user.service.UserVO;

public interface UserMapper {
	public UserVO loginCheck(String id);
	public int signup(UserVO userVO);
	public int idSearch(UserVO userVO);
	public int pwdSearch(UserVO userVO);
	public int idVaild(String id);

	
}
