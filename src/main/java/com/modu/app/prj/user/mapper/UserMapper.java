package com.modu.app.prj.user.mapper;

import java.util.List;
import java.util.Map;

import com.modu.app.prj.pay.service.PayVO;
import com.modu.app.prj.user.service.UserVO;

public interface UserMapper {
	public UserVO loginCheck(String id);
	public int signup(UserVO userVO);
	public UserVO idSearch(UserVO userVO);
	public int pwdUpdate(UserVO userVO);
	public String membSearch(UserVO userVO);
	public int idVaild(String id);
	public UserVO idCheck(String id);
	public UserVO emailAuth(String id);
	public int phNoVaild(String phNo);
	public int updateEmailAuthStatus(String token);
	public int updateNm(Map<String, String> params);
	public int updatePwd(Map<String, String> params);
	public int updateId(Map<String, String> params);
	public int updatePhone(Map<String, String> params);
	public int quitUser(String id);
	public List<UserVO> userList();
	public int userCount();
	public int newUsersCount();
	public List<Map<String, Object>> monthlyNewUsersCount();
	public UserVO myInfo(String id);
	public int totalPay();
	public List<PayVO> payTable();
	public int banUser(String id);
}
