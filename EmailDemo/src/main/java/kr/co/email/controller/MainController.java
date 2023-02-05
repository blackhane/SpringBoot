package kr.co.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.email.service.EmailSenderService;

@Controller
public class MainController {

	@Autowired
	private EmailSenderService emailSenderService;
	
	@GetMapping(value = {"", "index"})
	public String index() {
		return "emailForm";
	}
	
	@ResponseBody
	@PostMapping("EmailAuth/{jsonData}")
	public int EmailAuth(@PathVariable("jsonData") String jsonData) {
		int authNo = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;
		System.out.println("받는 이메일 : " + jsonData);
		System.out.println("인증번호 : " + authNo);
		
		String body = "인증코드 : " + authNo;
		
		emailSenderService.sendEmail("whynotcry94@gmail.com", jsonData, "스프링부트 이메일 인증번호 데모", body);
		
		return authNo;
	}
}
