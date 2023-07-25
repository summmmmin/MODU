package com.modu.app.prj.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.modu.app.prj.user.mapper.UserMapper;
import com.modu.app.prj.user.service.KakaoToken;
import com.modu.app.prj.user.service.UserService;
import com.modu.app.prj.user.service.UserVO;
import com.modu.app.sms.service.MessageDTO;
import com.modu.app.sms.service.SmsResponseDTO;
import com.modu.app.sms.service.SmsService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

	@Autowired
	KakaoToken kakaoToken;

	private final SmsService smsService;

	@PostMapping("sms")
	@ResponseBody
	public SmsResponseDTO sendSms(@RequestBody MessageDTO messageDTO)
			throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException {
		SmsResponseDTO response = smsService.sendSms(messageDTO);
		return response;
	}

	// 회원가입 사이트 이동
	@GetMapping("signup")
	public String signForm() {
		return "user/signup";
	}
	
	
	// 회원가입 폼에서 인증번호 확인
    @PostMapping("/verify")
    public String verify(@RequestParam("verificationCode") String verificationCode, HttpSession session) {
        // 세션에서 인증번호 가져오기
        String storedCode = (String) session.getAttribute("smsConfirmNum");

        // 인증번호 비교
        if (verificationCode.equals(storedCode)) {
            return "인증 성공";
        } else {
            return "인증 실패";
        }
    }
    
 // 회원가입 폼에서 아이디(이메일) 중복체크
    @PostMapping("/idvaild")
    public ResponseEntity<String> checkIdDuplicate(@RequestBody String id) {
        int duplicateCount = userService.idVaild(id);
        System.out.println(id);
        if (duplicateCount > 0) {
        	System.out.println("아이디 중복체크 : " + duplicateCount);
            return ResponseEntity.ok("이미 존재하는 아이디입니다.");		//그냥 public string하면 이동해야 할 view를 지정해줘야 해서 ResponseEntity 사용
            														//ResponseEntity 객체를 사용하여 HTTP 응답의 상태 코드와 헤더, 바디를 모두 직접 제어O 
            														//문자열이 view 이름으로 인식되는 것을 방지
        } else {
        	System.out.println("아이디 중복체크 : " +duplicateCount);
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        }
    }

	// 회원가입 처리
	@PostMapping("signup")
	public String signup(UserVO userVO, Model model) {
		
		// 사용자가 입력한 비밀번호를 가져옴
		String rawPassword = userVO.getPassword();

		// BCryptPasswordEncoder를 이용하여 비밀번호 암호화
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String encryptedPassword = scpwd.encode(rawPassword);																																								
		System.out.println("암호화된 비밀번호: " + encryptedPassword);
		userVO.setPassword(encryptedPassword);
		
		 // 토큰 생성
        String token = userService.generateRandomToken();
        userVO.setToken(token);

        userService.signup(userVO);
		return "redirect:login";
	}
	
//	  계정활성화N 유저
//    @PostMapping("emailAuth")
//    @ResponseBody
//    public String emailAuth(@RequestBody String id) {
//        UserVO user = userService.emailAuth(id);
//        if (user == null) {
//            return "존재하지 않는 유저";
//        }
//
//        if ("N".equalsIgnoreCase(user.getEmailAuth())) {
//            return "<small>계정활성화를 진행해주세요.</small>";
//        } else {
//
//            return "";
//        }
//    }
	
	// 회원가입 폼에서 휴대폰번호 중복체크
    @PostMapping("phNoVaild")
    public ResponseEntity<String> checkIdDuplicate1(@RequestBody String phNo) {
        int duplicateCount = userService.phNoVaild(phNo);
        System.out.println("회원가입 휴대폰 번호 : "  + phNo);
        if (duplicateCount > 0) {
        	System.out.println("휴대폰 중복체크 : " + duplicateCount);
            return ResponseEntity.ok("이미 존재하는 번호입니다.");	
        } else {
        	System.out.println("휴대폰 중복체크 : " +duplicateCount);
            return ResponseEntity.ok("사용 가능한 번호입니다.");
        }
    }

	// 사이트 회원 아이디 찾기
	@GetMapping("idSearch")
	public String idSearchForm(UserVO userVO) {
		return "user/idSearch";
	}
	
	//아이디 찾기 
	@PostMapping("idSearch")
	@ResponseBody
	public String idSearch(@RequestParam("name") String name, @RequestParam("phone") String phone) {
	    try {
	        UserVO userVO = new UserVO();
	        userVO.setNm(name);
	        userVO.setPhNo(phone);

	        String id = userService.idSearch(userVO); 

	        if (id == null) {
	            return "아이디가 존재하지 않습니다.";
	        } else {
	            return "아이디: " + id; // 아이디 값을 반환하도록 수정
	        }
	    } catch (Exception e) {
	        return "서버 오류가 발생했습니다." + e;
	    }
	}

	// 사이트 회원 비밀번호 찾기페이지(팝업창으로 뜸)
	@GetMapping("pwdSearch")
	public String pwdSearchForm(UserVO userVO) {
		return "user/pwdSearch";
	}

	// 사이트 회원 비밀번호 찾기
	@PostMapping("pwdSearch")
	public @ResponseBody String pwdSearch(@RequestParam("id") String id) {
		String newPassword = generateRandomPassword();

		// 이메일 발송
		SendEmail.gmailSend(id, newPassword);

		System.out.println("비밀번호 재설정 완료 " + newPassword);

		// 비밀번호 재설정
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO.setPwd(newPassword);

		// 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncoder.encode(newPassword);
		userVO.setPwd(encryptedPassword);

		userMapper.pwdSearch(userVO);

		return newPassword;
	}

	// 비밀번호 재설정 때 사용할 랜덤 비밀번호
	private String generateRandomPassword() {
		// 생성할 비밀번호의 길이
		int passwordLength = 8;

		// 알파벳 대소문자와 숫자를 포함한 모든 가능한 문자
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		// 무작위 문자열을 생성하기 위한 StringBuilder사용
		StringBuilder randomPassword = new StringBuilder(passwordLength);

		// 랜덤 객체 생성
		Random random = new Random();

		// 비밀번호 길이만큼 무작위 문자 선택하여 문자열 생성
		for (int i = 0; i < passwordLength; i++) {
			int index = random.nextInt(characters.length());
			char randomChar = characters.charAt(index);
			randomPassword.append(randomChar);
		}

		return randomPassword.toString();
	}


	// 카카오 oauth방식 로그인
	@RestController
	@AllArgsConstructor
	@RequestMapping("oauth")
	public class OAuthController {

		@ResponseBody
		@GetMapping("kakao")
		public void kakaoCallback(@RequestParam String code) {
			System.out.println(code);
			String access_Token = kakaoToken.getKaKaoAccessToken(code);

			HashMap<String, Object> userInfo = kakaoToken.getUserInfo(access_Token);
			System.out.println("###access_Token#### : " + access_Token);
			System.out.println("###nickname#### : " + userInfo.get("nickname"));
			System.out.println("###email#### : " + userInfo.get("email"));
			System.out.println("카카오 토큰 발급 : " + access_Token);
		}
	}
    
	/*
	 *  아래로는 로그인 유저 컨트롤러 아래로는 로그인 유저 컨트롤러 아래로는 로그인 유저 컨트롤러 아래로는 로그인 유저 컨트롤러 
	 */
    
    // 사이트 회원 마이페이지
	@GetMapping("loginuser/mypage")
	public String myPageForm(HttpServletRequest request, Model model) {
	    // 세션에서 사용자 정보 가져오기
	    HttpSession session = request.getSession();
	    UserVO userVO = (UserVO) session.getAttribute("user");

	    // 가져온 사용자 정보를 모델에 담아서 뷰로 전달
	    model.addAttribute("userVO", userVO);

	    return "user/loginuser/mypage";
	}


	// 사이트 회원 수정
	@GetMapping("userModify")
	public String userModifyForm(UserVO userVO) {
		return "user/userModify";
	}

	// 사이트 회원 정보 수정 로직
//	@PostMapping("userModify")
//	public String userModify(UserVO userVO) {
//		
//	}


}
