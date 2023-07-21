package com.modu.app.prj.user.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoToken {

	public String getKaKaoAccessToken(String code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=68caebee74c619b1dd3dfb0dab9d7a4c"); // TODO REST_API_KEY 입력
			sb.append("&redirect_uri=http%3A%2F%2Flocalhost%2Fmodu%2Foauth%2Fkakao"); // TODO 인가코드 받은 redirect_uri 입력
			sb.append("&code=" + code);
			sb.append("&client_secret=x8uItBbAwaBWPdZNkjszC9qJtMQUj5XL");
			System.out.println("발급된 코드 : "  + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonElement element = JsonParser.parseString(result);
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}
	
	// 카카오 액세스토큰에서 유저정보 추출
	public HashMap<String, Object> getUserInfo (String access_token) {
		HashMap<String, Object> userInfo = new HashMap<>();
	    String reqURL = "https://kapi.kakao.com/v2/user/me";
	    try{
	    	URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_token);   
	        int responseCode = conn.getResponseCode();       
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8")); 
	        String line = "";
	        String result = "";    
	        while ((line = br.readLine()) != null) {
	        	result += line;
	        }
	        
			JsonElement element = JsonParser.parseString(result);

	        String id = element.getAsJsonObject().get("id").getAsString();
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();    
	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        userInfo.put("id", id);
	        userInfo.put("name", nickname);
	        br.close();
	            
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	        
	    return userInfo;
	}


}
