package com.modu.app.prj.pay.service;

import java.util.List;

// 2023-07-22 하수민 프로젝트결제
public interface PayService {
	
	// 프로젝트 결제 내역
	public List<PayVO> prjPayList(String prjUniNO);
	
}
