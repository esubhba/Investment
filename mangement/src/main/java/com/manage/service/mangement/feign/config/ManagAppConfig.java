package com.manage.service.mangement.feign.config;

import java.util.Locale;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;


@Configuration
public class ManagAppConfig {

	
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
	
	

//	    @Bean
//	    public RequestInterceptor requestTokenBearerInterceptor() {
//	        return new RequestInterceptor() {
//				
//		
//	            @Override
//	            public void apply(RequestTemplate requestTemplate) {
//					/*
//					 * OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
//					 * SecurityContextHolder.getContext().getAuthentication().getDetails();
//					 * requestTemplate.header("Authorization", "bearer " + details.getTokenValue());
//					 */	    client.getServices().stream().filter(String::equalsIgnoreCase("MANAGE-SERVICE")).            
////                    requestTemplate.header("X-Forwarded-Host", .getFeign().getGatewayHost());
////                    requestTemplate.header("X-Forwarded-Port", applicationProperties.getFeign().getGatewayPort());
////                    requestTemplate.header("X-Forwarded-Proto", applicationProperties.getFeign().getGatewayProtocol());
////                    requestTemplate.header("X-Forwarded-Prefix", applicationProperties.getFeign().getServiceBPrefix());
////	                
//	            }
//	        };
	    

  
	  
	
}
