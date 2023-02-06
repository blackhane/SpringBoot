package kr.co.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailDemoApplication.class, args);
		
		//ConfigurableApplicationContext applicationContext = SpringApplication.run(EmailDemoApplication.class, args);
		
		//EmailSenderService emailSenderService = applicationContext.getBean(EmailSenderService.class);
		
		//emailSenderService.sendEmail("whynotcry94@gmail.com", "black_hana@naver.com", "Sending email using Spring Boot", "Email Send Test");
	}

}
