package kr.co.demo.service;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class KakaoOAuth2UserService extends DefaultOAuth2UserService{

	private final HttpSession httpsession;
	
	@Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    	OAuth2User oAuth2User = super.loadUser(userRequest);
    	Map<String, Object> attributes = oAuth2User.getAttributes();

        log.info("attributes :: "+attributes);    	
    	
        httpsession.setAttribute("login_info", attributes);

        return new DefaultOAuth2User( Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), attributes, "id" );
    }
	
}
