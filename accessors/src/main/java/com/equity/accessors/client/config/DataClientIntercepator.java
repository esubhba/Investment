package com.equity.accessors.client.config;

import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class DataClientIntercepator implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		template
		.header("Accept", "*")
		.header("Content-Type", "application/hal+json")
		.header("Content-Type",
				"application/json");
		

	}

}
