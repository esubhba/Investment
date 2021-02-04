package za1975.equity.invest.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import za1975.equity.invest.controller.InvestmentController;
import za1975.equity.invest.entity.Investment;
import za1975.equity.invest.model.InvestmentModel;

@Component
public class InvestmentAssembler extends RepresentationModelAssemblerSupport<Investment, InvestmentModel> {

	public InvestmentAssembler() {
		super(InvestmentController.class, InvestmentModel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InvestmentModel toModel(Investment entity) {
		InvestmentModel model=InvestmentModel.builder()
				.amount(entity.getAmount())
				.depositeBy(entity.getDepositeBy())
				.depositedOn(entity.getDepositedOn())
				.build();
		model.add(linkTo(methodOn(InvestmentController.class, 
				entity.getId()).getInvestmentDteails(entity.getId())).withSelfRel());
		return model;
	}

	public Investment toEntity(InvestmentModel investmentModel,String createdBy) {
		Investment investment = new Investment();
		investment.setAmount(investmentModel.getAmount());
		investment.setCreatedBy(createdBy);
		investment.setModifiedBy(createdBy);
		investment.setDepositeBy(investmentModel.getDepositeBy());
		investment.setDepositedOn(investmentModel.getDepositedOn());
		investment.setId(investmentModel.getId());
		investment.setModifiedBy(createdBy);
		investment.setModifiedOn(LocalDateTime.now());
		investment.setType(investmentModel.getType());
		
		return investment;
	}

}
