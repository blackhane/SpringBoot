package kr.co.sboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.sboard.Service.UserService;
import kr.co.sboard.vo.TermsVO;
import kr.co.sboard.vo.UserVO;

@MapperScan("kr.co.sboard.dao")
@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/user/register")
	public String register() {
		return "/user/register";
	}
	
	@PostMapping("/user/register")
	public String register(UserVO vo, HttpServletRequest request) {
		//사용자 IP 값
		vo.setRegip(request.getRemoteAddr());
		
		int result = service.insertUser(vo);
		if(result > 0) {
			//회원가입 성공
			result = 101;
		}else {
			//회원가입 실패
			result = 100;
		}
		
		return "redirect:/user/login?success=" + result;
	}
	
	@GetMapping("/user/terms")
	public String terms(Model model) {
		TermsVO vo = service.selectTerms();
		model.addAttribute("vo", vo);
		return "/user/terms";
	}
	
	@ResponseBody
	@GetMapping("/user/checkUid")
	public Map<String, Integer> checkUid(String uid) {
		int result = service.countUser(uid);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	
	@ResponseBody
	@GetMapping("/user/checkNick")
	public Map<String, Integer> checkNick(String nick) {
		int result = service.countNick(nick);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
}
