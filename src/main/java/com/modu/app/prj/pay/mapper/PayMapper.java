package com.modu.app.prj.pay.mapper;

import java.util.List;

import com.modu.app.prj.pay.service.PayVO;

// 2023-07-22 하수민 프로젝트 결제
public interface PayMapper {
	// 프로젝트 결제 내역
	public List<PayVO> selectPrjPay(String prjUniNo);
	
	// 프로젝트 결제 성공
	public int insertPay(PayVO pay);
	
	// 결제중인 프로젝트 리스트
	public List<PayVO> subscriPrj();
	
	// 프로젝트 구독 상태 변경
	public int updateStat(PayVO vo);
}
