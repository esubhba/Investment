package com.za1975.user.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.za1975.user.manage.models.Credentials;
import com.za1975.user.manage.service.UserService;
import com.za1975.user.manage.util.Constants;
import com.za1975.user.manage.util.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserSecurityFilter extends UsernamePasswordAuthenticationFilter {

	private UserService userService;
	private Environment env;
	
	
	public UserSecurityFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
		super( authenticationManager);
		this.userService = userService;
		this.env = env;		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {		  

		try (ServletInputStream is = request.getInputStream()){			   
			    log.info(is);	
				Credentials crd=new ObjectMapper().readValue(is,Credentials.class);
			    //Credentials crd=new Credentials(name, password);
				return getAuthenticationManager().authenticate(
						new UsernamePasswordAuthenticationToken(
								crd.getUsername(), 
								crd.getPassword(), new ArrayList<>())
						);
			    
			    
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info("#################### In Success Filter ####################################");
		UserDetails user=(UserDetails) authResult.getPrincipal();
		log.info(user.toString());
		log.info("Owner Name "+env.getProperty("owner-name")+" Token Valid Duration : "+env.getProperty(Constants.TOKEN_EXPIRATION_TIME,Long.class));
		userService.isLoggedIn(user.getUid());
		String token=Jwts.builder()
				.setSubject(user.getUid())
				.claim("userName", user.getUsername())
				.setExpiration((new Date((System.currentTimeMillis()+env.getProperty(Constants.TOKEN_EXPIRATION_TIME,Long.class)))))
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS512, env.getProperty(Constants.TOKEN_SECRIT_KEY))
				.compact();
		response.addHeader("x-auth-token", token);
		//super.successfulAuthentication(request, response, chain, authResult);
	}
	
	
	

}
