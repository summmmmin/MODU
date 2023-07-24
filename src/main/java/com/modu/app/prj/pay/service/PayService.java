package com.modu.app.prj.pay.service;

import java.util.List;

// 2023-07-22 하수민 프로젝트결제
public interface PayService {
	
	// 프로젝트 결제 내역
	public List<PayVO> prjPayList(String prjUniNO);
	
	// 결제 준비
	public PayVO kakaoPayReady(PayVO pay);
	
	// 결제 승인 요청
	public PayVO approveResponse(String pgToken);
	
	// 결제 테이블 insert
	public int insertPay(PayVO pay);
	
	// 정기결제요청
	public PayVO kakaoPaySubscrip(PayVO pay);
	
	// 정기결제 비활성화
	
	// 정기결제 상태 조회
}
