package za1975.equity.invest.assembler;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import za1975.equity.invest.controller.ShareController;
import za1975.equity.invest.entity.Share;
import za1975.equity.invest.model.ShareModel;

public class ShareAssembler extends RepresentationModelAssemblerSupport<Share, ShareModel>{

	public ShareAssembler() {
		super(ShareController.class, ShareModel.class);
		
	}

	@Override
	public ShareModel toModel(Share entity) {
		
		ShareModel model=ShareModel.builder()
				.code(entity.getCode())
				.exchangeName(entity.getExchangeName())
				.details(entity.getDetails())
				.build()
				.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ShareController.class,entity.getCreatedBy())
						.getAllShares(entity.getCreatedBy(),null,null)).withRel(IanaLinkRelations.SELF));
				//.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ShareController.class,entity.getCreatedBy())
						//..slash(entity));
		return model;
	}

}
