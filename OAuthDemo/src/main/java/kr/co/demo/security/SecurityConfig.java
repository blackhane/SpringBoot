package kr.co.demo.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final kr.co.demo.service.KakaoOAuth2UserService KakaoOAuth2UserService;
	
	protected void configure(HttpSecurity http) throws Exception {
		http.oauth2Login().userInfoEndpoint().userService(KakaoOAuth2UserService);
	}
	//https://kakao-tam.tistory.com/54
	
}
