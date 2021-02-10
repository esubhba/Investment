package za1975.equity.invest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import za1975.equity.invest.entity.StockDetails;
import za1975.equity.invest.model.StockSummery;
import za1975.equity.invest.repository.StockRepository;
import za1975.equity.invest.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;
	
	
	
	@Override
	public StockSummery getSummeryByShareID(Long shareId)
	{
		return stockRepository.findSummeryDetails(shareId);
	}
	
	@Override
	public Page<StockDetails> getTransactionHistory(Long shareId,Pageable pageable)
	{
		return stockRepository.findAllByShareId(shareId,pageable);
	}

}
