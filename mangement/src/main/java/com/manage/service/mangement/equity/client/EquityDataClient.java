package com.manage.service.mangement.equity.client;

import java.util.Locale;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manage.service.mangement.equity.model.ShareModel;

import feign.Headers;
import feign.Param;

@FeignClient(value = "dataClient" ,name = "DATA-SERVICE", 
fallbackFactory = ClientSupportFactory.class)
public interface EquityDataClient {
	
	@RequestMapping(path="/equity/{userId}/shares",consumes = {MediaTypes.HAL_JSON_VALUE},method= RequestMethod.GET)
	public PagedModel<ShareModel> getShares(@PathVariable(name = "userId") String userId,			
			@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable defaultPage,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale
			);

}

