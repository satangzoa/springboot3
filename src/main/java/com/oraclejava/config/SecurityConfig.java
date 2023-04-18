package com.oraclejava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.oraclejava.UserDetailServiceimpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailServiceimpl userDetailService;
	
	
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
//				.antMatchers("/","/**").permitAll()
				.antMatchers("/").permitAll() //접근 허가 익명사용
				.antMatchers("/login").permitAll() //사용자에게 로그인창 접근권한 허가
				.antMatchers("/css/**","/images/**").permitAll() //css images 허용하게한다
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated(); // 기타 나머지는 직접 접근 금지
		
		//로그인 처리
		http
			.formLogin() // --> 403페이지가 아니라 기본 로그인 창으로 이동
				.loginProcessingUrl("/login") //로그인 처리 경로(form action속성 값)  action의 url
				.loginPage("/login") //로그인 페이지 지정(컨트롤러의 RequestMapping Value 값) .get
				.failureUrl("/login?error")//로그인에 실패할 경우 이동하는 url
				.usernameParameter("userId") //사용자 id (input type = "text" 태그의 name값)
				.passwordParameter("password") // 비번 psssword (input type = "psssword" 태그의 name값)
				.defaultSuccessUrl("/", true) // 로그인 성공시 이동하는 url(보통 홈페이지), 
												//true -> 무조건 홈으로 이동, false -> 요청하는 url에 따라 달라지짐
			.and().httpBasic();
		
		
		//로그아웃 처리
		http
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
		
		// Remember Me 설정
//		http.rememberMe()
//				.key("uniqueKeyAndSecret") // 다른 고유한 키 지정하면 좋음
//				.rememberMeParameter("remember-me") // 체크박스의 name에 설정한 값
//				.rememberMeCookieName("remember-me")
//				.tokenValiditySeconds(86400); //86400초 
//		
		http.rememberMe()
				.key("uniqueKeyAndSecret")
				.rememberMeParameter("remember-me")
				.rememberMeCookieName("remember-me")
				.tokenValiditySeconds(86400);
	}
	
	
	
	//configure는 디비에 있는 정보를 가져온다
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//			.withUser("oracle") //로그인 화면 뜨면 아이디로 입력한다
//			.password(passwordEncoder().encode("java"))
//			.roles("USER");
		auth
			.userDetailsService(userDetailService)
			.passwordEncoder(passwordEncoder());
		
		
	}
	
}














