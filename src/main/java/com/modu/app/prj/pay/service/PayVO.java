package com.modu.app.prj.pay.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 2023-07-22 하수민 프로젝트 결제 vo (결제)
@Data
public class PayVO {
	
	private String payUniNo;	//결제번호
	private String prjUniNo;	//프로젝트번호
	private String membUniNo;	//회원번호
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	private Date payDt;			//결제일시
	private String payWay;		//결제방식 (현금, 카드)
	private int payAmt;			//결제금액
	private String cardName;	//카드이름
	private String name;		//결제한사람 이름
	private String email;		//이메일
	private String phone;		//전화번호
}
