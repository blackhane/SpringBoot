package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	//이메일 전송 튜토리얼
	public void sendEmail(String fromEmail,String toEmail,String subject,String body) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		//보내는 사람 메일
		mailMessage.setFrom(fromEmail);
		//받는 사람 메일
		mailMessage.setTo(toEmail);
		//이메일 제목
		mailMessage.setSubject(subject);
		//이메일 내용
		mailMessage.setText(body);
		
		mailSender.send(mailMessage);
		
		//이메일 전송 성공시 출력!
		System.out.println("Email Send Successfully!!");
		
	}
	
	
}
