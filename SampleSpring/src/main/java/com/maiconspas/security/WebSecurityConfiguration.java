package com.maiconspas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        //web.debug(true);
    }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		 auth.userDetailsService(userService)
				 .passwordEncoder(new BCryptPasswordEncoder());
		 auth.authenticationProvider(authenticationProvider);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	    http
          .authorizeRequests()
            .antMatchers("/**").hasAnyRole("ADMIN","USER").and()
          .httpBasic()
            .and()
		   .csrf().disable();		   
    }

}
