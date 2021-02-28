package com.equity.accessors.client.config;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DataClientErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		log.info("Call From Error Decoder "+ methodKey+ " "+response);
		if(response.status()>=400 && response.status()<=499) {
			log.error(" Bad Request : Http Statu Code "+response.status()+" Method Key "+methodKey+"  "+response.reason());
			return new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);
		}
		return defaultErrorDecoder.decode(methodKey, response);
		
	}

}
