package tacos.security;

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


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http 
		.authorizeRequests()
			.antMatchers("/design", "/orders")
				.access("hasRole('ROLE_USER')")
			.antMatchers("/","/**").access("permitAll")
		
		.and() // 인증 구성이 끝났으므로 새로운 구성을 시작하겠다.
			.formLogin()
				.loginPage("/login")
		.and()
			.logout()
				.logoutSuccessUrl("/")
	    .and()
	    	.csrf()
	    ;
		
	}
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(encoder());
	}
	
	
//  인메모리 방식으로 사용자 스토어 구성 - 간단한 테스트 목적으로만 사용할 것.	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		.inMemoryAuthentication()
//		.withUser("user1")
//		.password("{noop}password1")
//		.authorities("ROLE_USER")
//		.and()
//		.withUser("user2")
//		.password("{noop}password2")
//		.authorities("ROLE_UESR");
//	}
}
