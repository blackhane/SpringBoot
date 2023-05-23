package kr.co.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.board.service.UserService;
import kr.co.board.vo.TermsVO;
import kr.co.board.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String root() {
		return "redirect:/user/login";
	}
	
	@GetMapping("user/login")
	public String login(@RequestParam(required = false) String success, Model model) {
		
		model.addAttribute("success", success);
		
		return "user/login";
	}
	
	@GetMapping("user/terms")
	public String terms(Model model) {
		
		TermsVO vo = service.selectTerms();
		model.addAttribute("vo", vo);
		
		return "user/terms";
	}

	@GetMapping("user/register")
	public String register() {
		return "user/register";
	}
	
	@PostMapping("user/register")
	public String register(UserVO vo, HttpServletRequest req) {
		
		vo.setRegip(req.getRemoteAddr());
		service.insertUser(vo);

		return "redirect:/user/login?success=101";
	}
	
	@ResponseBody
	@GetMapping("checkUid")
	public Map<String, Integer> checkUid(String uid) {

		int rs = service.selectUid(uid);
		
		Map<String, Integer> result = new HashMap<>();
		result.put("rs", rs);
		
		return result;
	}
	
	@ResponseBody
	@GetMapping("checkNick")
	public Map<String, Integer> checkNick(String nick) {
		
		int rs = service.selectNick(nick);
		
		Map<String, Integer> result = new HashMap<>();
		result.put("rs", rs);
		
		return result;
	}
	
}
