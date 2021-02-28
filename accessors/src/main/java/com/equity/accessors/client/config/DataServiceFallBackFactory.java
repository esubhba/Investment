package com.equity.accessors.client.config;

import java.util.Locale;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import com.equity.accessors.client.DataServiceClient;
import com.equity.accessors.data.model.ShareModel;

import lombok.extern.log4j.Log4j2;

@Component
public class DataServiceFallBackFactory implements FallbackFactory<DataServiceClient> {

	@Override
	public DataServiceClient create(Throwable cause) {		
		return new MyDataServiceClient(cause);
	}

}
@Log4j2
class MyDataServiceClient implements DataServiceClient
{

	private Throwable cause;

	public MyDataServiceClient(Throwable cause) {
		this.cause=cause;
	}

	@Override
	public PagedModel<ShareModel> getShares(String userId, Pageable defaultPage, Locale locale) {
		log.info("From Circuit Breaker "+cause);
		return PagedModel.empty();
	}
	
}
