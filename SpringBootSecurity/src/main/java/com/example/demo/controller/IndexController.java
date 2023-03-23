package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

	@GetMapping("")
	public String index() {
		return "index";
	}

	@PostMapping("login")
	public String login(String uid, String password) {
		return "redirect:/loginSuccess";
	}
	
	@GetMapping("loginSuccess")
	public String loginSuccess() {
		return "loginSuccess";
	}
	
}
