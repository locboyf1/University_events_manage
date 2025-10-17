package com.event.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/admin/**").hasAuthority("quantrivien")
				.requestMatchers("/", "/trangchu", "/dangnhap", "/dangxuat", "/assets/**", "/admins/**",
						"/testpassword/**", "/api/**")
				.permitAll()

				.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/dangnhap").loginProcessingUrl("/dangnhap")
						.defaultSuccessUrl("/trangchu", true).permitAll())
				.logout(logout -> logout.logoutUrl("/dangxuat").logoutSuccessUrl("/").permitAll())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

		return http.build();
	}
}
