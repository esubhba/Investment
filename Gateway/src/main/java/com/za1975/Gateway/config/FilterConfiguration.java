package com.za1975.Gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Configuration
@Log4j2
public class FilterConfiguration {

	@Bean	
	public GlobalFilter firstFilter()
	{
		return (exchange,chain)->
		{
			log.info("################################ Pre Filter Executed  ##################################");
			
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
				log.info("################################ Post Filter Executed ##################################");
				
//				exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
//		        exchange.getResponse().getHeaders().add("X-Frame-Options", "SAMEORIGIN");
//		        exchange.getResponse().getHeaders().add("X-Xss-Protection", "1; mode=block");
//		        exchange.getResponse().getHeaders().add("X-Content-Type-Options", "nosniff");
//		        exchange.getResponse().getHeaders().add("Content-Security-Policy", "block-all-mixed-content; base-uri 'self'; frame-ancestors 'self'; form-action 'self'; object-src 'none';");
//		        exchange.getResponse().getHeaders().add("Referrer-Policy", "no-referrer-when-downgrade");
//		        exchange.getResponse().getHeaders().add("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
//		        exchange.getResponse().getHeaders().add("Feature-Policy", "microphone 'none'; geolocation 'none'");
//		        exchange.getResponse().getHeaders().add("X-Permitted-Cross-Domain-Policies", "none");
//		        exchange.getResponse().getHeaders().add("Expect-CT", "max-age=86400, enforce");
//		        exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS,PATCH,PUT,DELETE");
				
			}));
		};
	}
}
