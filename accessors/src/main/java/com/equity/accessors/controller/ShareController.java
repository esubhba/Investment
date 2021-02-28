package com.equity.accessors.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equity.accessors.data.model.ShareModel;
import com.equity.accessors.service.DataService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping( path = "/access",consumes = {MediaTypes.HAL_JSON_VALUE,MediaType.APPLICATION_JSON_VALUE}
,produces =  {MediaTypes.HAL_JSON_VALUE,MediaType.APPLICATION_JSON_VALUE})
@Log4j2
public class ShareController {

	@Autowired
	private DataService dataService;
	
	@GetMapping(path = "{userId}/shares")	
	public ResponseEntity<?> getAllShares(@PathVariable("userId")String userId,
			@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable defaultPage,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale)
	{
		    log.info("From /access/"+userId+"/shares");
			PagedModel<ShareModel> result = dataService.getShares(userId, defaultPage, locale);
			return ResponseEntity.ok(result);
			
	}
}
