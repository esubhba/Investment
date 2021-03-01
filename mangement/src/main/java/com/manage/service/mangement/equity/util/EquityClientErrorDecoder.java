package com.manage.service.mangement.equity.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EquityClientErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder defaultErrorDecoder = new Default();
	@Override
	public Exception decode(String methodKey, Response response) {
		log.error("Feign Error Started");
		if(response.status()>=400 && response.status()<=499) {
			log.error(" Bad Request : Http Statu Code "+response.status()+" Method Key "+methodKey+"  "+response.reason());
			return new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);
		}
		return defaultErrorDecoder.decode(methodKey, response);
	}

}
