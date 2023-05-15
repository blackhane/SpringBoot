package kr.co.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/user/**").permitAll();
		http.authorizeRequests().antMatchers("/list").hasAnyRole("1");
		http.authorizeRequests().antMatchers("/write").hasAnyRole("1");
		http.authorizeRequests().antMatchers("/view").hasAnyRole("1");
		http.authorizeRequests().antMatchers("/modify").hasAnyRole("1");
		
		http.formLogin()
			//로그인 페이지
			.loginPage("/user/login")
			//아이디 파라미터 설정(기본 username)
			.usernameParameter("id")
			//패스워드 파라미터 설정
			.passwordParameter("pw")
			//성공시
			.defaultSuccessUrl("/list")
			//실패시
			.failureUrl("/user/login?success=100");
		
		http.logout()
			.invalidateHttpSession(true)
			//로그아웃 호출주소
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessUrl("/user/login?success=103");
	}
	
	@Autowired
	private SecurityUserService securityUserService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityUserService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**");
	}

}
