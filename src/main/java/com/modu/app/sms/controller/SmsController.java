package com.modu.app.sms.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.modu.app.sms.service.MessageDTO;
import com.modu.app.sms.service.SmsResponseDTO;
import com.modu.app.sms.service.SmsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Controller
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public SmsResponseDTO sendSms(@RequestBody MessageDTO messageDTO) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        SmsResponseDTO responseDTO = smsService.sendSms(messageDTO);
        return responseDTO;
    }
}