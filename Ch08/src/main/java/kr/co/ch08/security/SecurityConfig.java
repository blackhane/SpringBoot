package kr.co.ch08.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//접근권한
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER");
		http.authorizeRequests().antMatchers("/member/**").hasAnyRole("ADMIN", "MANAGER", "MEMBER");
	
		//사이트위조 방지설정
		http.csrf().disable();
		
		//로그인
		http.formLogin().loginPage("/user2/login").defaultSuccessUrl("/user2/loginSuccess").usernameParameter("uid").passwordParameter("pass");
		
		//로그아웃 설정
		http.logout().invalidateHttpSession(true).logoutRequestMatcher(new AntPathRequestMatcher("/user2/logout")).logoutSuccessUrl("/user2/login");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Security 사용자에 대한 권한 설정
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("manager").password("{noop}1234").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");
		
	}
	
}
