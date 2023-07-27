package com.modu.app.prj.user.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.modu.app.prj.user.service.UserVO;

public class SendEmail {
	public static void gmailSend(String userId, String newPassword) {
		String user = "qomo596@gmail.com"; // 발신 gmail 계정
		String password = "mehkjbnookiyggaa"; // gmail 패스워드(2단계 앱보안 비밀번호 테스트파일에 넣어둠)

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance (prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소 (회원아이디)
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userId));

			// 메일제목
			message.setSubject("MODU 사이트의 비밀번호 찾기를 요청하셨습니다");

			// 내용
			String emailContent = "안녕하세요,\n\n새로운 비밀번호로 재설정되었습니다.\n\n새 비밀번호: " + newPassword;
			message.setText(emailContent);

			Transport.send(message); //// 전송
			
			
			System.out.println("비밀번호 재설정 메일 발송완료");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void authSend(UserVO userVO, String siteURL) {
        String user = "qomo596@gmail.com"; // 발신 gmail 계정
        String password = "mehkjbnookiyggaa"; // gmail 패스워드(2단계 앱보안 비밀번호 테스트파일에 넣어둠)

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance (prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            // 수신자메일주소 (회원아이디)
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userVO.getId()));

            // 메일제목
            message.setSubject("MODU 사이트 회원가입 인증 메일");

            // 내용
            String emailContent = "<h1>회원가입 인증 메일입니다.</h1>";
            emailContent += "<p>아래 링크를 클릭하여 계정을 활성화하세요:</p>";
            emailContent += "<a href=\"" + siteURL + "/activate?token=" + userVO.getToken() + "\">활성화 링크</a>";

            message.setContent(emailContent, "text/html; charset=utf-8");

            Transport.send(message); //전송
            
            System.out.println("인증 메일 발송완료");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	public static void idMail(String userId, String newEmail, String authCode) {
	    String user = "qomo596@gmail.com"; // 발신 gmail 계정
	    String password = "mehkjbnookiyggaa"; // gmail 패스워드(2단계 앱보안 비밀번호 테스트파일에 넣어둠)

	    // SMTP 서버 정보를 설정한다.
	    Properties prop = new Properties();
	    prop.put("mail.smtp.host", "smtp.gmail.com");
	    prop.put("mail.smtp.port", 465);
	    prop.put("mail.smtp.auth", "true");
	    prop.put("mail.smtp.ssl.enable", "true");
	    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	    Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	        }
	    });

	    try {
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(user));

	        //수신자메일주소
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(newEmail)); 

	        // Subject
	        message.setSubject("MODU사이트에서 이메일 변경을 요청하셨습니다."); // 메일 제목을 입력

	        // Text
	        message.setText("인증 번호 : " + authCode + "를 입력하세요."); // 메일 내용을 입력

	        // send the message
	        Transport.send(message);
	        System.out.println("아이디 변경 메일 발송 완료");
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}


	
	
	
	
//	테스트용 코드
//	public static void main(String[] args) {
//		String userId = "user@example.com"; // 수신자 이메일 주소
//		String newPassword = "newPassword"; // 새로운 비밀번호
//
//		SendEmail.gmailSend(userId, newPassword);
//
//		System.out.printf("이메일 전송되었습니다 > " + userId, newPassword);
//	}

}