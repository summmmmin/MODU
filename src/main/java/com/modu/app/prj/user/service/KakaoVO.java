package com.modu.app.prj.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class KakaoVO {
	private Long id;
	private String email;
	private String nickname;
//  private String profileImgUrl;
//  private String thumnailImgUrl;
//
//  private String birthday;
//  private boolean hasBirthDay;
//  private String gender;
//  private boolean hasGender;
}
