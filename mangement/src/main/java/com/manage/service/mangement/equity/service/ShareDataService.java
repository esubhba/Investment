package com.manage.service.mangement.equity.service;

import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;

import com.manage.service.mangement.equity.model.ShareModel;

public interface ShareDataService {

	

	PagedModel<ShareModel> getAllShares(String userId, Pageable page, Sort sort,
			Locale locale);
}
