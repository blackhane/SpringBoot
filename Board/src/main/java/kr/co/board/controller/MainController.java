package kr.co.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("list")
	public String list() {
		return "list";
	}
	
	@GetMapping("write")
	public String write() {
		return "write";
	}

	@GetMapping("view")
	public String view() {
		return "view";
	}
	
	@GetMapping("modify")
	public String modify() {
		return "modify";
	}
	
}
