package com.modu.app.prj.user.service;

import java.util.List;
import java.util.Map;

import com.modu.app.prj.pay.service.PayVO;

public interface UserService {
	public int signup(UserVO userVO);
	public String idSearch(UserVO userVO);
	public int pwdUpdate(UserVO userVO);
	public String membSearch(UserVO userVO);
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
	public List<UserVO> userList();
	public int userCount();
	public int newUsersCount();
	public List<Map<String, Object>> monthlyNewUsersCount();
	public UserVO myInfo(String id);
	public int totalPay();
	public List<PayVO> payTable();
	public int banUser(String id);
}
