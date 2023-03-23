package kr.co.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Autowired
	private kakaoService kakaoservice;
	
	@GetMapping("auth/kakao")
	public String kakao() {
		return "kakao";
	}
	@GetMapping("auth/naverLogin")
	public String naver() {
		return "naver";
	}
	/*
	    1. 클라이언트 쪽에서 로그인을 한다

		2. 카카오 서버는 redirect url로 code를 전달해준다
		
		3,4. code를 이용하여 access_token을 발급받는다
		
		5. access_token을 서버로 전송한다
		
		6,7. 서버에서는 받은 access_token을 이용하여 카카오 서버에서 사용자 정보를 받는다
		
		8. 받은 사용자 정보를 이용하여 회원가입 또는 로그인을 진행한다
		
		9. JWT등과 같이 사용자 식별 정보를 클라이언트로 보낸다
	 */
	@GetMapping("/auth/kakao/callback")
	@ResponseBody
	public String kakao(String code) {
		kakaoservice.getAccessToken(code);
		return "code : " + code;
	}
}
