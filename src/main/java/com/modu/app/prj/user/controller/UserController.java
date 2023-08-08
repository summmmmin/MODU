package com.modu.app.prj.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.modu.app.prj.pay.service.PayVO;
import com.modu.app.prj.user.mapper.UserMapper;
import com.modu.app.prj.user.service.PrincipalDetails;
import com.modu.app.prj.user.service.UserService;
import com.modu.app.prj.user.service.UserVO;
import com.modu.app.sms.service.MessageDTO;
import com.modu.app.sms.service.SmsResponseDTO;
import com.modu.app.sms.service.SmsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	@Autowired
    SendEmail sendEmail;
	
	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

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
		if (duplicateCount > 0) {
			return ResponseEntity.ok("이미 존재하는 아이디입니다."); // 그냥 public string하면 이동해야 할 view를 지정해줘야 해서 ResponseEntity 사용
																										// ResponseEntity 객체를 사용하여 HTTP 응답의 상태 코드와 헤더, 바디를 모두 직접 제어O
																										// 문자열이 view 이름으로 인식되는 것을 방지
		} else {
			return ResponseEntity.ok("사용 가능한 아이디입니다.");
		}
	}

	// 회원가입 처리
	@PostMapping("signup")
	public String signup(UserVO userVO, Model model, HttpServletRequest request) {

		// 사용자가 입력한 비밀번호를 가져옴
		String rawPassword = userVO.getPassword();

		// BCryptPasswordEncoder를 이용하여 비밀번호 암호화
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String encryptedPassword = scpwd.encode(rawPassword);
		userVO.setPassword(encryptedPassword);

		// 토큰 생성
		String token = userService.generateRandomToken();
		userVO.setToken(token);

		userService.signup(userVO);

		// 회원가입 후 이메일 발송
		String siteURL = getSiteURL(request);
		sendEmail.authSend(userVO, siteURL);

		return "redirect:login";
	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	// 회원가입 폼에서 휴대폰번호 중복체크
	@PostMapping("phNoVaild")
	public ResponseEntity<String> checkphNoDuplicate(@RequestBody String phNo) {
		int duplicateCount = userService.phNoVaild(phNo);
		if (duplicateCount > 0) {
			return ResponseEntity.ok("이미 존재하는 번호입니다.");
		} else {
			return ResponseEntity.ok("사용 가능한 번호입니다.");
		}
	}

	// 아이디 활성화 링크
	@GetMapping("/activate")
	public String updateEmailAuthStatus(@RequestParam("token") String token, RedirectAttributes ra) {
		String result = userService.updateEmailAuthStatus(token);

		if ("success".equals(result)) {
			ra.addFlashAttribute("message", "계정 활성화에 성공했습니다. 로그인하여 이용하세요.");
			return "redirect:/login";
		} else {
			ra.addFlashAttribute("message", "계정 활성화에 실패했습니다. 잘못된 접근이거나 만료된 인증 링크입니다.");
			return "redirect:/login";
		}
	}

	// 사이트 회원 아이디 찾기
	@GetMapping("idSearch")
	public String idSearchForm(UserVO userVO) {
		return "user/idSearch";
	}

	// 아이디 찾기
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
				return "회원님의 아이디는 " + id + " 입니다."; // 아이디 값을 반환하도록 수정
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
	public ResponseEntity<String> pwdSearch(@RequestParam("id") String id, @RequestParam("nm") String nm) {
	    UserVO userVO = new UserVO();
	    userVO.setId(id);
	    userVO.setNm(nm);

	    String membUniNo = userService.membSearch(userVO);
	    if (membUniNo != null && !membUniNo.isEmpty()) {
	        String newPassword = generateRandomPassword();

	        userVO.setPwd(newPassword);
	        userVO.setMembUniNo(membUniNo);

	        userService.pwdUpdate(userVO);

	        // 이메일 전송
			sendEmail.gmailSend(id, newPassword);

	        return ResponseEntity.ok("비밀번호가 재설정 되었습니다. 메일을 확인해주세요.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 회원입니다.");
	    }
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

	// 소셜로그인
	@GetMapping("info/oauth/login")
	public Map<String, Object> oauthLoginInfo(Authentication authentication,
			@AuthenticationPrincipal OAuth2User oAuth2UserPrincipal) {
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		Map<String, Object> attributes = oAuth2User.getAttributes();

		return attributes; // 세션에 담긴 user
	}

	@GetMapping("info/loginInfo")
	public String loginInfo(Authentication authentication) {
		String result = "";
		Object principal = authentication.getPrincipal();

		if (principal instanceof PrincipalDetails) {
			PrincipalDetails principalDetails = (PrincipalDetails) principal;
			// OAuth2 로그인 처리
			result = "OAuth2 로그인 : " + principalDetails;
		} else if (principal instanceof UserVO) {
			// Form 로그인 처리
			UserVO user = (UserVO) principal;
			result = "Form 로그인 : " + user;
		}
		return result;
	}

	/*
	 * 아래로는 로그인 유저 컨트롤러 아래로는 로그인 유저 컨트롤러 아래로는 로그인 유저 컨트롤러 아래로는 로그인 유저 컨트롤러
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

	// 이름 변경
	@PostMapping("loginuser/modifyNm")
	@ResponseBody
	public ResponseEntity<String> modifyNm(HttpServletRequest request, @RequestParam("newName") String newName) {
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");

		String userId = userVO.getId();

		// 파라미터 2개 받느라 Map에 넣어서 값넘김
		Map<String, String> params = new HashMap<>();
		params.put("id", userId);
		params.put("newName", newName);

		String updateResult = userService.updateNm(params);

		if ("이름 변경 성공".equals(updateResult)) {
			userVO.setNm(newName);
			session.setAttribute("user", userVO);
			return ResponseEntity.ok("이름 변경 성공");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이름 변경 실패");
		}
	}

	// 비밀번호 변경
	@PostMapping("loginuser/modifyPwd")
	@ResponseBody
	public ResponseEntity<String> modifyPwd(HttpServletRequest request,
			@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword) {
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");

		String userId = userVO.getId();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedNewPassword = passwordEncoder.encode(newPassword);

		String storedPassword = userVO.getPassword();

		// 파라미터 2개 > Map에 넣어서 값넘김
		Map<String, String> params = new HashMap<>();
		params.put("id", userId);
		params.put("newPassword", encodedNewPassword);

		// 비밀번호 일치하는지 체크
		if (passwordEncoder.matches(currentPassword, storedPassword)) {
			String updateResult = userService.updatePwd(params);

			if ("비밀번호 변경 성공".equals(updateResult)) {
				userVO.setPassword(encodedNewPassword);
				session.setAttribute("user", userVO);

				return ResponseEntity.ok("비밀번호 변경 성공");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 변경 실패");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("현재 비밀번호가 일치하지 않습니다");
		}
	}

	// 휴대폰 번호 변경
	@PostMapping("loginuser/modifyPhone")
	@ResponseBody
	public ResponseEntity<String> modifyPhone(HttpServletRequest request,
			@RequestBody Map<String, String> phoneNumberRequest) {
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");

		String userId = userVO.getId();
		String newPhoneNumber = phoneNumberRequest.get("newPhoneNumber");

		// 파라미터 2개 > Map에 넣어서 값넘김
		Map<String, String> params = new HashMap<>();
		params.put("id", userId);
		params.put("phNo", newPhoneNumber);

		String updateResult = userService.updatePhone(params);

		if ("휴대폰 번호 변경 성공".equals(updateResult)) {
			userVO.setPhNo(newPhoneNumber);
			session.setAttribute("user", userVO);
			return ResponseEntity.ok("휴대폰 번호 변경 성공");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("휴대폰 번호 변경 실패");
		}
	}

	// 이메일 변경
	@PostMapping("loginuser/modifyEmail")
	@ResponseBody
	public ResponseEntity<String> modifyEmail(String newEmail, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");

		Map<String, String> verificationCodes = new HashMap<>();

		String verificationCode = userService.emailCode();
		verificationCodes.put(userVO.getId(), verificationCode);

		// 세션에 인증번호 저장
		session.setAttribute("storedCode", verificationCode);

		sendEmail.idMail(userVO.getId(), newEmail, verificationCode);

		return ResponseEntity.ok("이메일 전송 성공");
	}

	@PostMapping("loginuser/emailVerify")
	@ResponseBody
	public ResponseEntity<String> emailVerify(@RequestParam String newEmail, @RequestParam String code,
			HttpServletRequest request) {
		HttpSession session = request.getSession();

		UserVO userVO = (UserVO) session.getAttribute("user");

		String storedCode = (String) session.getAttribute("storedCode");
		try {
			newEmail = URLDecoder.decode(newEmail, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (storedCode != null && storedCode.equals(code)) {

			Map<String, String> params = new HashMap<>();
			params.put("id", userVO.getId());
			params.put("newEmail", newEmail); //
			userService.updateId(params);

			session.removeAttribute("storedCode");
			return ResponseEntity.ok("아이디 변경 성공");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디 변경 실패");
		}
	}

	// 회원 탈퇴
	@PostMapping("loginuser/quitUser")
	public ResponseEntity<String> quitUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			userService.quitUser(username);

			// 로그아웃 (세션 종료)
			SecurityContextHolder.clearContext();
			return ResponseEntity.ok("탈퇴 처리 완료");
		} else {
			return ResponseEntity.badRequest().body("사용자가 아님");
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 관리자 대시보드
	@GetMapping("admin/dashBoard")
	public String dashboard(Model model) {
		int userCount = userService.userCount();
		int newUsersCount = userService.newUsersCount();
		List<Map<String, Object>> monthlyNewUsersCount = userService.monthlyNewUsersCount();
		int totalPay = userService.totalPay();
		model.addAttribute("userCount", userCount);
		model.addAttribute("newUsersCount", newUsersCount);
		model.addAttribute("monthlyNewUsersCount", monthlyNewUsersCount);
		model.addAttribute("totalPay", totalPay);

		return "admin/dashBoard";
	}

	// 관리자 대시보드 그래프
	@GetMapping("admin/dashBoard/monthlyNewUsersCount")
	@ResponseBody
	public List<Map<String, Object>> getMonthlyNewUsersCount() {
		return userService.monthlyNewUsersCount();
	}

	// 관리자 유저목록
	@GetMapping("admin/userList")
	public String userList(Model model) {
		List<UserVO> userList = userService.userList();
		model.addAttribute("userList", userList);
		return "admin/userList";
	}

	// 유저 정보 조회(마이페이지)
	@PostMapping("admin/userList/{id}")
	@ResponseBody
	public UserVO myInfo(@PathVariable String id) {
		UserVO user = userService.myInfo(id);
		return user;
	}
	
    // 전체 결제 내역 조회
    @GetMapping("admin/payTable")
    public String payTable(Model model) {
        List<PayVO> payTable = userService.payTable();
        model.addAttribute("payTable", payTable);
        return "admin/payTable";
    }
    
    //유저추방
    @PostMapping("admin/banUser")
    public ResponseEntity<String> banUser(@RequestBody UserVO userVO) {
        try {
            userService.banUser(userVO.getId());
            return ResponseEntity.ok("회원이 추방되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
