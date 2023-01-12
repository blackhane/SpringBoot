package kr.co.ch06.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.ch06.vo.UserVO;

@Controller
public class MainController {

	@GetMapping(value = {"/", "/index"})
	public String index(Model model) {
		
		String tit = "Spring Boot!";
		String name = "Thymeleaf!";
		
		UserVO user1 = new UserVO();
		user1.setUid("a101");
		user1.setName("홍길동");
		user1.setHp("000-1111-2222");
		user1.setAge(21);
		
		UserVO user2 = null;
		
		List<UserVO> users = new ArrayList<>();
		users.add(new UserVO("a102", "김유신", "000-1111-2222", 19));
		users.add(new UserVO("a103", "김춘추", "000-1111-3333", 25));
		users.add(new UserVO("a104", "장보고", "000-1111-4444", 35));
		users.add(new UserVO("a105", "강감찬", "000-1111-5555", 40));
		users.add(new UserVO("a106", "이순신", "000-1111-6666", 55));
		
		model.addAttribute("tit", tit);
		model.addAttribute("name", name);
		model.addAttribute("user1", user1);
		model.addAttribute("user2", user2);
		model.addAttribute("users", users);
		
		return "/index";
	}
	
	@GetMapping("/include")
	public String include() {
		return "/include";
	}
	
	@GetMapping("/layout")
	public String layout() {
		return "/layout";
	}
}
