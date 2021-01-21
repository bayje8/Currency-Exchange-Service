package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableConfigurationProperties(AuthSecuredProperties.class)
@EnableWebSecurity
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthSecuredProperties props;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/actuator").hasAnyAuthority(props.getAdminRole())
				.antMatchers("/CurrencyExchange/**").hasAnyAuthority(props.getUserRole())
				.antMatchers("/info").hasAnyAuthority(props.getGuestRole())
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin().and()
			.httpBasic();
	}
	
	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth
 		// enable in memory based authentication with a user named "user" and "admin"
 		.inMemoryAuthentication()
 			.withUser(props.getApiUserName()).password("{noop}"+props.getApiPassword()).authorities(props.getUserRole())
 		.and()
 			.withUser(props.getAdminUserName()).password("{noop}"+props.getAdminPassword()).authorities(props.getAdminRole(), props.getUserRole())
 		.and()
 			.withUser(props.getGuestUserName()).password("{noop}"+props.getGuestPassword()).authorities(props.getGuestRole());
 	}

}
