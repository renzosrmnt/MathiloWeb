package com.tienda.mathiloweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpSession;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
	}
	protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(authz -> authz
        .requestMatchers("/categoria/**").hasAuthority("ADMIN")
        .anyRequest().authenticated())
		.formLogin(formLogin -> formLogin
                .loginPage("/login") 
                .usernameParameter("email").defaultSuccessUrl("/", true)
                .successHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession(true);
                    session.setMaxInactiveInterval(30 * 60);
                    response.sendRedirect("/");
                }).permitAll())
		.logout(logout -> logout
                .logoutUrl("/logout")
                .permitAll())
        .httpBasic(withDefaults());

		return  httpSecurity.build();
	}
}
