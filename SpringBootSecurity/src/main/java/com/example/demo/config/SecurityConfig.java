package com.example.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//페이지에 권한이 있는 사람만 출입가능
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		http.csrf().disable().authorizeHttpRequests().antMatchers("/login/**").hasAnyRole("admin","user").and().formLogin();
		http.csrf().disable().authorizeHttpRequests().antMatchers("/adminLogin/**").hasAnyRole("admin").and().formLogin();
		*/
		
		http.csrf().disable()
			.authorizeHttpRequests().antMatchers("/login/**").hasAnyRole("admin","user").and()
			.authorizeHttpRequests().antMatchers("/adminLogin/**").hasAnyRole("admin").and()
			.formLogin();
	}
	
	//권한부여
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.inMemoryAuthentication().withUser("jin1").password("1234").roles("user");
		auth.inMemoryAuthentication().withUser("jin2").password("1234").roles("user", "admin");
		*/
		
		auth.inMemoryAuthentication()
			.withUser("jin1").password("1234").roles("user").and()
			.withUser("jin2").password("1234").roles("user", "admin");
	}
	
}
