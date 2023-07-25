package com.modu.app.prj.pay.service;

import java.time.LocalDateTime;
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
	
	// 카카오페이
	// 결제 요청시 카카오에게 받음
	private String next_redirect_pc_url; //요청한 클라이언트가 PC 웹일 경우 카카오톡으로 결제 요청 메시지(TMS)를 보내기 위한 사용자 정보 입력 화면 Redirect URL
	
	// 카카오페이 결제 승인 요청 응답
 	private String aid; // 요청 고유 번호
    private String tid; // 결제 고유 번호
    private String cid; // 가맹점 코드
    private String sid; // 정기결제용 ID
    private String partner_order_id; // 가맹점 주문 번호
    private String partner_user_id; // 가맹점 회원 id
    private String payment_method_type; // 결제 수단 CARD 또는 MONEY 
    private Amount amount; // 결제 금액 정보
    private CardInfo card_info;	//결제 상세 정보, 결제수단이 카드일 경우만 포함
    private String item_name; // 상품명
    private String item_code; // 상품 코드
    private int quantity; // 상품 수량
    private LocalDateTime created_at; // 결제 요청 시간
    private LocalDateTime approved_at; // 결제 승인 시간
    private String payload; // 결제 승인 요청에 대해 저장 값, 요청 시 전달 내용
    
    
    // 프로젝트 테이블
//    PRJ_UNI_NO NOT NULL VARCHAR2(10)  
//    PRJ_NM     NOT NULL VARCHAR2(20)  
//    SUBSP_YN   NOT NULL CHAR(1)       
//    EXDT                DATE          
//    PAY_TOKEN           VARCHAR2(30)  
//    PAY_UNI_NO          VARCHAR2(150) 
}
