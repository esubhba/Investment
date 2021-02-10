package za1975.equity.invest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import za1975.equity.invest.entity.StockDetails;
import za1975.equity.invest.model.StockSummery;

public interface StockService {

	StockSummery getSummeryByShareID(Long shareId);

	Page<StockDetails> getTransactionHistory(Long shareId, Pageable pageable);

}
