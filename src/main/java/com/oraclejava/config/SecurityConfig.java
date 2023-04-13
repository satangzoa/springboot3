package com.oraclejava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//암호화 모듈 Bean 정의
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티 관련 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//직접 접근금지 및 로그인이 필요없는 페이지 설정
		http
		//Cross site Request forgery로 사이즈간 위조 요청인데, 
		//즉 정상적인 사용자가 의도치 않은 위조요청을 보내는 것을 의미
			.csrf().disable() //csrf 무효화
			.authorizeRequests()
				.antMatchers("/","/cgv/**","/customers/**").permitAll() //접근 허가
				.antMatchers("/css/**","/images/**").permitAll() //css images 허용하게한다
				.anyRequest().authenticated(); // 기타 나머지는 직접 접근 금지
				
	}
}














