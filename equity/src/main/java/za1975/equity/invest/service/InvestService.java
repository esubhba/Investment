package za1975.equity.invest.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import za1975.equity.invest.entity.Investment;

public interface InvestService {

	Page<Investment> getInvestmentDetails(Pageable pageable);

	Optional<Investment> getInvestmentDetailsById(Long id);

	Investment createNew(Investment investment);
	
	

}
