package za1975.equity.invest.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import za1975.equity.utils.DepositType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true )
@Entity
@Table(name = "invest_details",schema="equity")
public class Investment extends BasicInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7954855182858350808L;

	@Id
	@SequenceGenerator(name = "invest_details_seq",
	sequenceName  = "invest_details_seq",
	allocationSize = 1,initialValue =1, 
	schema = "equity")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invest_details_seq")
	@Generated(GenerationTime.INSERT)
	private Long id;
	
	@Include
	@ColumnDefault("now()")
	@Column(name="deposit_on" ,nullable = false)
	private LocalDateTime depositedOn;
	
	@Include
	@Enumerated(EnumType.STRING)
	@Column(name = "deposit_type")
	private DepositType type;
	
	@Column(name="amount",precision = 8,scale = 2,nullable = false)	
	private BigDecimal amount;
	
	@Include
	@Column(name="deposit_withdraw_by",nullable = true)
	private String depositeBy;
	
	
}
