package kr.co.sboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

	@GetMapping("/list")
	public String list() {
		return "/list";
	}
	
	@GetMapping("/write")
	public String write() {
		return "/write";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return "/modify";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "/delete";
	}
	
	
}
