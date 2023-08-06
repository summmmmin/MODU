package com.modu.app.prj.pay.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.modu.app.prj.pay.mapper.PayMapper;
import com.modu.app.prj.pay.service.PayService;
import com.modu.app.prj.pay.service.PayVO;
import com.modu.app.prj.prj.mapper.PrjMapper;

@Service
public class PayServiceImpl implements PayService {
	static final String cid = "TCSUBSCRIP"; // 정기결제 가맹점 테스트 코드
	
	 @Value("${kakao-admin-key}")
    private String admin_Key; // 애플리케이션 어드민 키
	 
    private PayVO kakaoReady;
    private PayVO payInfo;
    
	@Autowired
	PayMapper payMapper;
	@Autowired
	PrjMapper prjMapper;

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
        parameters.add("total_amount", "4800");				// 상품 총액
        parameters.add("tax_free_amount", "0");				// 상품 비과세 금액
        parameters.add("approval_url", "http://43.201.17.213:85/modu/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "http://43.201.17.213:85/modu/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "http://43.201.17.213:85/modu/payment/fail"); // 실패 시 redirect url		
        
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
		
    	MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("sid", pay.getPayToken());
        parameters.add("partner_order_id", pay.getPrjUniNo());
        parameters.add("partner_user_id", pay.getMembUniNo());
        parameters.add("item_name", "유료플랜 정기결제");
        parameters.add("quantity", "1");
        parameters.add("total_amount", "4800");
        parameters.add("tax_free_amount", "0");
        
        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        PayVO approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/subscription",
                requestEntity,
                PayVO.class);
        
        approveResponse.setName(pay.getName());
        approveResponse.setPhone(pay.getPhone());
        approveResponse.setEmail(pay.getEmail());
        return approveResponse;
	}

	public PayVO kakaoPayInactive(String sid) {
		// 정기결제 비활성화
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("sid", sid);
        
        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        PayVO inactiveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/manage/subscription/inactive",
                requestEntity,
                PayVO.class);
                
        return inactiveResponse;
	}
	
	public PayVO kakaoPayStatus(String sid) {
		// 정기결제 상태 조회
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("sid", sid);
        
        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        PayVO statusResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/manage/subscription/status",
                requestEntity,
                PayVO.class);
                
        return statusResponse;
	}
	
	@Override
	public int insertPay(PayVO pay) {
		// 결제 완료 시 db에 데이터 넣기
		return payMapper.insertPay(pay);
	}
	
	@Scheduled(cron = "0 0 10 * * *") 
	//@Scheduled(fixedDelay = 10000)
	public void run() {
		LocalDate date = LocalDate.now().minusDays(1);	// 어제날짜

    	// 정기결제 중인 프로젝트 목록 조회
		List<PayVO> subList = payMapper.subscriPrj();
		for(PayVO vo : subList) {
			if(vo.getSubspYn().equals("Y") && vo.getExdt().equals(date)) {
				// 구독 여부 'Y'이고 만료일자 +1 = 현재날짜 -> 정기결제 요청
				PayVO result = kakaoPaySubscrip(vo);
				// 실패시 구독 여부 'E' 만료일자 결제토큰 결제번호 삭제
				
				// 정기결제 성공 시 db에 저장
				insertPay(result);
			}else if(vo.getSubspYn().equals("C") && vo.getExdt().equals(date)) {
				// 구독 여부 'C'이고 만료일자 +1 = 현재날짜 -> 정기결제 비활성화
				kakaoPayInactive(vo.getPayToken());
				
				// 구독 여부 'N' 바꾸기 만료일자 결제토큰 결제번호 삭제
				vo.setSubspYn("N");
				payMapper.updateStat(vo);
			}
		}

    }

}
