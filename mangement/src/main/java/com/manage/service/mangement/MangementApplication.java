package com.manage.service.mangement;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHypermediaSupport(type = { HypermediaType.HAL_FORMS, HypermediaType.HAL })
public class MangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangementApplication.class, args);
	}

	@Bean
	public Decoder halModule() {
		return new JacksonDecoder(List.of(new Jackson2HalModule()));
	}
	
	
}
