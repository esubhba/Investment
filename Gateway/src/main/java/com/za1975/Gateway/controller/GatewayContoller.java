package com.za1975.Gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/service")
public class GatewayContoller {
	
	@Autowired
	private Environment env;
	
	@GetMapping
	public ResponseEntity<String> getService()
	{
		return ResponseEntity.ok(env.getProperty("owner-name","Not Found"));
	}

}
