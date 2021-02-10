package za1975.equity.invest.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import za1975.equity.invest.assembler.StockAssembler;
import za1975.equity.invest.entity.Share;
import za1975.equity.invest.entity.StockDetails;
import za1975.equity.invest.model.StockSummery;
import za1975.equity.invest.service.ShareService;
import za1975.equity.invest.service.StockService;
import za1975.equity.utils.EquityException;

@RestController
@RequestMapping(path = "/{shareId}/stocks")
@OpenAPIDefinition(info = @Info(title = "Stocks Api"),tags = @Tag(name = "Stock Api"))
public class StockController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private StockAssembler stockAssembler;
	
	@Autowired
	private PagedResourcesAssembler<StockDetails> pageAssembler;
	
	@GetMapping(path = "/summery")
	public ResponseEntity<?> getSummeryDetails(@PathVariable(name = "shareId")Long shareId,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE,required = false)Locale locale)
	{
		Optional<Share> entityO=shareService.getShareDetailsById(shareId);
		Share entity=entityO.orElseThrow(()->
		{
			String msg = messageSource.getMessage("SH000.Message", new Object[] { shareId }, LocaleContextHolder.getLocale());
			String title= messageSource.getMessage("SH000.Title", new Object[] { shareId}, LocaleContextHolder.getLocale());
			throw new EquityException(title,msg,null);
		});
		StockSummery model = stockService.getSummeryByShareID(shareId);
		model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
				.methodOn(ShareController.class, entity.getCreatedBy())
				.getShareDetails(entity.getCreatedBy(), shareId, locale)).withRel("share"));
		model.add(WebMvcLinkBuilder.linkTo(StockController.class,shareId,locale).slash("transaction-history").withRel(IanaLinkRelations.ARCHIVES));
		return ResponseEntity.ok(model);
	}
	
	@GetMapping("/transaction-history")
	public ResponseEntity<?> getTransactionHistory(@PathVariable(name="shareId")Long shareId,@PageableDefault(size = 25,page = 1,
	direction = Direction.DESC)Pageable page,@RequestHeader(name = "Accept-Language",required = false) Locale locale){		
		return ResponseEntity.ok(pageAssembler.toModel(stockService.getTransactionHistory(shareId, page), stockAssembler));
	}

}
