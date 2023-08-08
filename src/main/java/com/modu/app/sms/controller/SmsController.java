package com.modu.app.sms.controller;

import java.io.UnsupportedEncodingException;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.modu.app.sms.service.MessageDTO;
import com.modu.app.sms.service.SmsRequestDTO;
import com.modu.app.sms.service.SmsResponseDTO;
import com.modu.app.sms.service.SmsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Controller
public class SmsController {

	private final SmsService smsService;

	@PostMapping("send")
	public SmsResponseDTO sendSms(@RequestBody MessageDTO messageDTO) throws UnsupportedEncodingException,
			URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
		SmsResponseDTO responseDTO = smsService.sendSms(messageDTO);
		return responseDTO;
	}

	@PostMapping("/verifyCode")
	public ResponseEntity<Object> verifyCode(@RequestBody VerificationCodeRequest request) {
		String code = request.getCode();
		boolean isValid = smsService.isSmsCodeValid(code);
		return ResponseEntity.ok(new VerificationResponse(isValid));
	}

	static class VerificationCodeRequest {
		private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

	static class VerificationResponse {
		private boolean isValid;

		public VerificationResponse(boolean isValid) {
			this.isValid = isValid;
		}

		public boolean isValid() {
			return isValid;
		}
	}
}