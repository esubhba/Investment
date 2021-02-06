package za1975.equity.invest.service;

import za1975.equity.invest.model.StockSummery;

public interface StockService {

	StockSummery getSummeryByShareID(Long shareId);

}
