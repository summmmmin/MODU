package com.modu.app.prj.pay.service;

import lombok.Data;

@Data
public class Amount {
	// 카카오페이 amount 결제 금액 정보
	private int total; // 총 결제 금액
    private int tax_free; // 비과세 금액
    private int vat; // 부가세 금액
    private int point; // 사용한 포인트
    private int discount; // 할인금액
    private int green_deposit; // 컵 보증금
}
