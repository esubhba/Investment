package com.equity.accessors.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.equity.accessors.client.DataServiceClient;
import com.equity.accessors.data.model.ShareModel;
import com.equity.accessors.service.DataService;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private DataServiceClient dataClient;
	
	@Override
	public PagedModel<ShareModel> getShares(String userId,Pageable pageable,Locale locale)
	{
		return dataClient.getShares(userId, pageable, locale);
	}

}
