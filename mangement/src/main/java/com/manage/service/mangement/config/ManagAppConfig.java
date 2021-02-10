package com.manage.service.mangement.config;

import java.util.Locale;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class ManagAppConfig {

	@Bean
	public SimpleModule halModule() {		
		return new Jackson2HalModule();
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();  
		localeResolver.setDefaultLocale(Locale.forLanguageTag("hi-IN"));  
		return localeResolver;  
	}
	@Bean
	public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter()
	{
		FilterRegistrationBean<ForwardedHeaderFilter> filterRegistrationBean=new FilterRegistrationBean<ForwardedHeaderFilter>();
		filterRegistrationBean.setFilter(new ForwardedHeaderFilter());
		filterRegistrationBean.setOrder(0);
		return filterRegistrationBean;
	}
	
	
}
