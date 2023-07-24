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

public class SendEmail {
	public static void gmailSend(String userId, String newPassword) {
		String user = "qomo596@gmail.com"; // 발신 gmail 계정
		String password = "mehkjbnookiyggaa"; // gmail 패스워드(2단계 앱보안 비밀번호)

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

	public static void main(String[] args) {
		String userId = "user@example.com"; // 수신자 이메일 주소
		String newPassword = "newPassword"; // 새로운 비밀번호

		SendEmail.gmailSend(userId, newPassword);

		System.out.printf("이메일 전송되었습니다 > " + userId, newPassword);
	}

}