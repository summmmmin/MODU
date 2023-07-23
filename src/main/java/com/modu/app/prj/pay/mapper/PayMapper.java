package com.modu.app.prj.pay.mapper;

import java.util.List;

import com.modu.app.prj.pay.service.PayVO;

// 2023-07-22 하수민 프로젝트 결제
public interface PayMapper {
	// 프로젝트 결제 내역
	public List<PayVO> selectPrjPay(String prjUniNo);
}
