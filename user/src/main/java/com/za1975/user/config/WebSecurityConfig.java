package com.za1975.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import com.za1975.user.filter.UserSecurityFilter;
import com.za1975.user.manage.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	@Autowired
	private Argon2PasswordEncoder argon2PasswordEncoder;
	@Autowired
	private Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();		
		http.headers().frameOptions().disable();
		http.authorizeRequests()
				/*
				 * .antMatchers("/users/register/**","/users/login/**") .permitAll() .and()
				 * .httpBasic() .and() .authorizeRequests().anyRequest().authenticated() .and()
				 */
		.antMatchers("/user/**")
		.hasIpAddress("localhost")
		.and()
		.addFilter(getAuthenticationFilter());
		//.addFilterBefore(new UserSecurityFilter(userService,env,authenticationManager()),
				//UsernamePasswordAuthenticationFilter.class);
		//.sessionManagement()
		//.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}

	

	private UserSecurityFilter getAuthenticationFilter() throws Exception {
		UserSecurityFilter filter = new UserSecurityFilter(userService,env,authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(argon2PasswordEncoder);
		super.configure(auth);
	}

}
