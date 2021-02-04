package com.za1975.user.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
public class UserAppConfiguration {
	
	@Bean
	public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter()
	{
		FilterRegistrationBean<ForwardedHeaderFilter> filterRegistrationBean=new FilterRegistrationBean<ForwardedHeaderFilter>();
		filterRegistrationBean.setFilter(new ForwardedHeaderFilter());
		filterRegistrationBean.setOrder(0);
		return filterRegistrationBean;
	}
	
	@Bean
	public Argon2PasswordEncoder argon2PasswordEncoder()
	{
		return new Argon2PasswordEncoder();
	}

}
