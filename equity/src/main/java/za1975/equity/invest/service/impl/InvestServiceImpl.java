package za1975.equity.invest.service.impl;


import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import za1975.equity.invest.entity.Investment;
import za1975.equity.invest.repository.InvestRepository;
import za1975.equity.invest.service.InvestService;

@Service
@Log4j2
public class InvestServiceImpl implements InvestService {

	@Autowired
	private InvestRepository investRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public Page<Investment> getInvestmentDetails(Pageable pageable)
	{
		log.error(messageSource.getMessage("info", new Object[] {"Test @Log4j2"}, Locale.US));
		return investRepository.findAll(pageable);
		
	}

	@Override
	public Optional<Investment> getInvestmentDetailsById(Long id) {
		
		return investRepository.findById(id);
	}

	@Override
	public Investment createNew(Investment entity) {
		
		return investRepository.save(entity);
		
	}
}
