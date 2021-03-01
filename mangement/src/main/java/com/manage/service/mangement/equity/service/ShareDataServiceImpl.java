package com.manage.service.mangement.equity.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.manage.service.mangement.equity.client.EquityDataClient;
import com.manage.service.mangement.equity.model.ShareModel;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ShareDataServiceImpl implements ShareDataService {

	@Autowired
	private EquityDataClient equityClient;
	
	@Autowired
	private DiscoveryClient client;
	



	@Override
	public PagedModel<ShareModel> getAllShares(String userId, Pageable page, Sort sort, Locale locale) {
		page.getSortOr(sort);		
		return equityClient.getShares(userId,page,locale);
	}

	
}
