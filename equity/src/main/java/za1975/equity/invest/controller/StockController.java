package za1975.equity.invest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import za1975.equity.invest.service.StockService;

@RestController
@RequestMapping(path = "/{shareId}/stocks")
@OpenAPIDefinition(info = @Info(title = "Stocks Api"),tags = @Tag(name = "Stock Api"))
public class StockController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private StockService stockService;
	
	@GetMapping(path = "/summery")
	public ResponseEntity<?> getSummeryDetails(@PathVariable(name = "shareId")Long shareId)
	{
		return ResponseEntity.ok(stockService.getSummeryByShareID(shareId));
	}

}
