package za1975.equity.invest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
