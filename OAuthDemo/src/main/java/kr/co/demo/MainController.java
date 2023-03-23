package kr.co.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.demo.service.NaverService;
import kr.co.demo.service.kakaoService;
import kr.co.demo.vo.UserEntity;

@Controller
public class MainController {

	@Autowired
	private kakaoService kakaoService;
	@Autowired
	private NaverService naverService;
	
	//카카오 로그인 
	@GetMapping("auth/kakao")
	public String kakao(Model model, HttpSession session) {
		UserEntity entity = new UserEntity();
		entity = (UserEntity) session.getAttribute("userInfo");
		if(entity != null) {
			model.addAttribute("entity", entity);
		}
		session.removeAttribute("userInfo");
		return "kakao";
	}
	//카카오 로그인 토큰 발급
	@GetMapping("/auth/kakao/callback")
	public String kakao(String code, HttpSession session) {
		String token = kakaoService.getAccessToken(code);
		session.setAttribute("token", token);
		UserEntity entity = kakaoService.getKakaoUserInfo(token);
		session.setAttribute("userInfo", entity);
		return "redirect:/auth/kakao";
	}
	//카카오 로그아웃(서비스)
	@GetMapping("/auth/kakaoLogout")
	public String kakaoLogout (HttpSession session) {
		String token = (String) session.getAttribute("token");
		kakaoService.logout(token);
		return "redirect:/auth/kakao";
	}
	//카카오 로그아웃
	@GetMapping("/auth/kakaoUnlink")
	public String kakaoUnlink (HttpSession session) {
		String token = (String) session.getAttribute("token");
		kakaoService.unlink(token);
		return "redirect:/auth/kakao";
	}
	
	//네이버 로그인
	@GetMapping("auth/naver")
	public String naver() {
		return "naver";
	}
	//네이버 로그인 토큰 발급
	@GetMapping("auth/naver/callback")
	@ResponseBody
	public void naverCallback(String code) {
		String token = naverService.getAccessToken(code);
		naverService.getNaverUserInfo(token);
	}
	
}
