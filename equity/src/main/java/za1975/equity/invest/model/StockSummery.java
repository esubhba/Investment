package za1975.equity.invest.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za1975.equity.utils.MoneySerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public class StockSummery extends RepresentationModel<StockSummery>{
	@JsonProperty("share_cod")
	private String shareCode;
	@JsonProperty("exchange_code")
	private String shareExchange;
	@JsonProperty("quantity_on_hold")
	private int quantityOnHold;
	@JsonProperty("price")
	@JsonSerialize(using = MoneySerializer.class,as = BigDecimal.class)
	private BigDecimal price;
	@JsonProperty("total_quantity")
	private Long totalQuantity;
	@JsonProperty("total_profit")
	@JsonSerialize(using = MoneySerializer.class,as = BigDecimal.class)
	private BigDecimal profit;
	
}
