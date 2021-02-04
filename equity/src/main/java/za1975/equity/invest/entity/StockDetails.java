package za1975.equity.invest.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import za1975.equity.utils.TransactionType;

@Data
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
@Entity
@Table(name="stock_details",schema = "equity",
indexes = @Index(name="stock_idx",columnList = "share_id,transaction_type,transaction_date"),
uniqueConstraints = @UniqueConstraint(name="uq_transaction",columnNames = {"ref_order_id","share_id","transaction_type"}))
public class StockDetails extends BasicInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2624026140307618644L;

	@Id
	@SequenceGenerator(name = "stock_id_seq",
	sequenceName  = "stock_id_seq",
	allocationSize = 1,initialValue =1, 
	schema = "equity")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stock_id_seq")
	@Generated(GenerationTime.INSERT)
	private Long id;
	
	@Include
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name = "share_stock_id_fk",value = ConstraintMode.CONSTRAINT),name = "share_id")
	private Share share;
	
	@Column(name="transaction_date",nullable = false)
	private LocalDateTime transactionDate;
	
	@Include
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type",nullable = false)
	private TransactionType type;
	
	@Column(name="quantity",nullable = false)
	private int quantity;
	
	@Column(name="price",nullable = false,precision = 10,scale = 4)
	private BigDecimal price;
	
	@Include
	@Column(name="ref_transaction_id",nullable = false)
	private String transactionId;
	
	@Include
	@Column(name="ref_order_id",nullable = false)
	private String orderId;
	
	@Column(name="tax",nullable = true,precision = 8,scale = 4)
	private BigDecimal tax;
	
}
