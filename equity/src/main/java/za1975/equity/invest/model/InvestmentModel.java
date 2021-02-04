package za1975.equity.invest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import za1975.equity.utils.DepositType;
import za1975.equity.utils.MoneySerializer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Relation(collectionRelation = "invests",itemRelation = "invest")
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = Include.NON_NULL)
public class InvestmentModel extends RepresentationModel<InvestmentModel> {

	
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "UTC")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime depositedOn;	
	
	@NonNull
	private DepositType type;
	
	@JsonSerialize(using=MoneySerializer.class,as = BigDecimal.class)
	private BigDecimal amount;
	
	@JsonProperty("deposited_from")
	private String depositeBy;
}
