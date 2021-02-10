package com.manage.service.mangement.equity.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.manage.service.mangement.equity.client.EquityDataClient;
import com.manage.service.mangement.equity.model.ShareModel;

@Service
public class ShareDataServiceImpl implements ShareDataService {

	
	private EquityDataClient equityClient;
	
	
	@Autowired
	public ShareDataServiceImpl(EquityDataClient equityClient) {		
		this.equityClient = equityClient;
	}



	@Override
	public PagedModel<ShareModel> getAllShares(String userId, Pageable page, Sort sort, Locale locale) {
		page.getSortOr(sort);
		return equityClient.getAllShares(userId,page,locale);
	}

	
}
