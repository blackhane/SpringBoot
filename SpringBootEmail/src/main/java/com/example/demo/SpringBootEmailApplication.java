package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.service.EmailSenderService;

@SpringBootApplication
public class SpringBootEmailApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootEmailApplication.class, args);
		
		EmailSenderService emailSenderService = applicationContext.getBean(EmailSenderService.class);
		
		emailSenderService.sendEmail("whynotcry94@gmail.com", "black_hana@naver.com", "Sending email using Spring Boot", "Email Send Test");
	}

}
