package za1975.equity.invest.assembler;

import java.time.LocalDateTime;

import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import za1975.equity.invest.controller.ShareController;
import za1975.equity.invest.controller.StockController;
import za1975.equity.invest.entity.Share;
import za1975.equity.invest.entity.StockDetails;
import za1975.equity.invest.model.StockModel;

@Component
public class StockAssembler extends RepresentationModelAssemblerSupport<StockDetails, StockModel> {

	public StockAssembler() {
		super(StockController.class, StockModel.class);
		
	}

	@Override
	public StockModel toModel(StockDetails entity) {
		StockModel model=StockModel.builder()
				.id(entity.getId())
				.shareId(entity.getShare().getId())
				.orderId(entity.getOrderId())
				.price(entity.getPrice())
				.quantity(entity.getQuantity())
				.tax(entity.getTax())
				.transactionDate(entity.getTransactionDate())
				.transactionId(entity.getTransactionId())
				.type(entity.getType()).build();
		model.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(ShareController.class, entity.getCreatedBy())
					.getShareDetails(entity.getCreatedBy(), entity.getShare().getId(), null)
				).withRel(LinkRelation.of("share")));
		return model;
	}
	
	public StockDetails toEntity(StockModel model,String createdBy)
	{
		Share share=null;
		if(model.getShareId()!=null) {
			share=new Share();
			share.setId(model.getShareId());
		}
		StockDetails entity=new StockDetails();
		entity.setId(model.getId());
		entity.setCreatedBy(createdBy);
		entity.setCreatedOn(LocalDateTime.now());
		entity.setModifiedBy(createdBy);
		entity.setModifiedOn(LocalDateTime.now());
		entity.setOrderId(model.getOrderId());
		entity.setPrice(model.getPrice());
		entity.setQuantity(model.getQuantity());
		entity.setShare(share);
		entity.setTransactionDate(model.getTransactionDate());
		entity.setTransactionId(model.getTransactionId());
		entity.setType(model.getType());
		return entity;
	}

}
