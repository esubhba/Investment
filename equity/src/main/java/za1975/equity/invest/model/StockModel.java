package za1975.equity.invest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import za1975.equity.utils.MoneySerializer;
import za1975.equity.utils.TransactionType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonRootName("transaction")
@JsonInclude(Include.NON_NULL)
@Relation(collectionRelation = "transactions", itemRelation = "transaction")
public class StockModel extends RepresentationModel<StockModel> {
	
	@JsonIgnore
	private Long id;
	private Long shareId;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "UTC")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonProperty("trading_date")
	private LocalDateTime transactionDate;	
	
	@NotNull
	@JsonProperty("trade_type")
	private TransactionType type;	
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	@JsonSerialize(as = BigDecimal.class,using = MoneySerializer.class)
	private BigDecimal price;
	
	@NotNull
	@JsonProperty("trade_id")
	private String transactionId;
	
	@NotNull
	@JsonProperty("order_id")
	private String orderId;
	
	@JsonSerialize(as = BigDecimal.class,using = MoneySerializer.class)
	private BigDecimal tax;

}
