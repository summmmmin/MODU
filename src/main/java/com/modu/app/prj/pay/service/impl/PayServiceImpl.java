package com.modu.app.prj.pay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.modu.app.prj.pay.mapper.PayMapper;
import com.modu.app.prj.pay.service.PayService;
import com.modu.app.prj.pay.service.PayVO;

@Service
public class PayServiceImpl implements PayService {
	static final String cid = ""; // 정기결제 가맹점 테스트 코드
    static final String admin_Key = ""; // 애플리케이션 어드민 키
    private PayVO kakaoReady;
    private PayVO payInfo;
    
	@Autowired
	PayMapper payMapper;

	@Override
	public List<PayVO> prjPayList(String prjUniNO) {
		return payMapper.selectPrjPay(prjUniNO);
	}
	
	// 헤더값
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
    
	// 첫결제 준비
	@Override
	public PayVO kakaoPayReady(PayVO pay) {
		// 요청 시 넘어오는 값(회원아이디, 프로젝트번호, 이름, 이메일, 번호) 저장
		payInfo = pay;
		
		// 카카오페이에 요청할 값
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);							// 가맹점 코드, 10자
        parameters.add("partner_order_id", pay.getPrjUniNo());	// 가맹점 주문번호, 최대 100자
        parameters.add("partner_user_id", pay.getMembUniNo());	// 가맹점 회원 id, 최대 100자
        parameters.add("item_name", "유료플랜");				// 상품명, 최대 100자
        parameters.add("quantity", "1");					// 상품 수량
        parameters.add("total_amount", "12100");				// 상품 총액
        parameters.add("tax_free_amount", "0");				// 상품 비과세 금액
        parameters.add("approval_url", "http://localhost/modu/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "http://localhost/modu/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "http://localhost/modu/payment/fail"); // 실패 시 redirect url		
        
		// 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                PayVO.class);

        return kakaoReady;
	}

	@Override
	public PayVO approveResponse(String pgToken) {
        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);		//가맹점 코드, 10자
        parameters.add("tid", kakaoReady.getTid());		//결제 고유번호, 결제 준비 API 응답에 포함
        parameters.add("partner_order_id", payInfo.getPrjUniNo());	//가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
        parameters.add("partner_user_id", payInfo.getMembUniNo());	//가맹점 회원 id, 결제 준비 API 요청과 일치해야 함
        parameters.add("pg_token", pgToken);				//결제승인 요청을 인증하는 토큰 사용자 결제 수단 선택 완료 시, approval_url로 redirection해줄 때 pg_token을 query string으로 전달

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        
        PayVO approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                PayVO.class);
        
        approveResponse.setName(payInfo.getName());
        approveResponse.setPhone(payInfo.getPhone());
        approveResponse.setEmail(payInfo.getEmail());
        return approveResponse;
	}

	@Override
	public PayVO kakaoPaySubscrip(PayVO pay) {
		// 정기결제 요청
		
//    	MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("cid", cid);
//        parameters.add("sid", "S4b9db994cecf58662");
//        parameters.add("partner_order_id", "가맹점 주문 번호");
//        parameters.add("partner_user_id", "가맹점 회원 ID");
//        parameters.add("item_name", "상품명");
//        parameters.add("quantity", "1");
//        parameters.add("total_amount", "1");
//        parameters.add("tax_free_amount", "0");
//        
//        // 파라미터, 헤더
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
//        
//        // 외부에 보낼 url
//        RestTemplate restTemplate = new RestTemplate();
//
//        KakaoApproveResponse approveResponse = restTemplate.postForObject(
//                "https://kapi.kakao.com/v1/payment/subscription",
//                requestEntity,
//                KakaoApproveResponse.class);
//                
//        return approveResponse;
		
		return null;
	}

	@Override
	public int insertPay(PayVO pay) {
		return payMapper.insertPay(pay);
	}
	
	
}
