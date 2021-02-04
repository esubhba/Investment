package com.za1975.Gateway.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.za1975.Gateway.utils.Constants;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

	@Autowired
	private  Environment env;
	
	public AuthorizationFilter() {
		super(Config.class);
		// TODO Auto-generated constructor stub
	}

	public static class Config
	{
		//private String baseMessage;
	}

	@Override
	public GatewayFilter apply(Config config) {
		
		return (exchange,chain)->{
			ServerHttpRequest req = exchange.getRequest();
			if(!req.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
				return onError(exchange,"Request header "+HttpHeaders.AUTHORIZATION+" missing ");
			String authorizationCode=req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
			String jwtToken=authorizationCode.replace(env.getProperty(Constants.AUTHORIZATION_PREFIX_KEY),"");
			if(!isJwtValid(jwtToken))
				onError(exchange, "Not a valid authorization key");
			return chain.filter(exchange);
			
			
		};
	}

	private boolean isJwtValid(String jwtToken) {
		
		String userId=null;
		try {
			userId=Jwts.parser()
			.setSigningKey(env.getProperty(Constants.TOKEN_SECRIT_KEY))
			.parseClaimsJws(jwtToken)
			.getBody()
			.getSubject();
		}catch (Exception e) {
			return false;
		}
		
		if(userId==null|| userId !=null && userId.isEmpty())
			return false;
		return true;
	}

	private Mono<Void> onError(ServerWebExchange exchange, String string) {
		ServerHttpResponse res = exchange.getResponse();
		res.setStatusCode(HttpStatus.UNAUTHORIZED);		
		return res.setComplete();
		
	}

}
