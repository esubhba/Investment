package za1975.equity.invest.assembler;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import za1975.equity.invest.controller.ShareController;
import za1975.equity.invest.entity.Share;
import za1975.equity.invest.model.ShareModel;

@Component
public class ShareAssembler extends RepresentationModelAssemblerSupport<Share, ShareModel>{

	public ShareAssembler() {
		super(ShareController.class, ShareModel.class);
		
	}

	@Override
	public ShareModel toModel(Share entity) {
		
		ShareModel model=ShareModel.builder()
				.id(entity.getId())
				.code(entity.getCode())
				.exchangeName(entity.getExchangeName())
				.details(entity.getDetails())
				.build()
				.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ShareController.class,entity.getCreatedBy())
						.getAllShares(entity.getCreatedBy(),null,null,LocaleContextHolder.getLocale())).withRel(IanaLinkRelations.COLLECTION))
				.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ShareController.class,entity.getCreatedBy(),entity.getId())
						.getShareDetails(entity.getCreatedBy(), entity.getId(), null)).withRel(IanaLinkRelations.ITEM));
						//..slash(entity));
		return model;
	}

}
