package com.algaworks.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("rafael").password("{noop}s3nh4").roles("USER");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic() //
				.and().authorizeRequests().anyRequest().authenticated() //
				.antMatchers("/h2-console/**").permitAll() //
				.and().httpBasic()//
				.and().csrf().disable();
	}

}