package com.equity.accessors.service;

import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.equity.accessors.data.model.ShareModel;

public interface DataService {

	PagedModel<ShareModel> getShares(String userId, Pageable pageable, Locale locale);

	

}
