package za1975.equity.devident.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import za1975.equity.invest.entity.BasicInformation;
import za1975.equity.invest.entity.Share;

@Data
@Entity
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
@Table(name="devident_details",schema = "equity")
public class Devident extends BasicInformation{
	
	
	private static final long serialVersionUID = -7137342655667318563L;

	@Id
	@SequenceGenerator(name = "devident_id_seq",
	sequenceName  = "devident_id_seq",
	allocationSize = 1,initialValue =1, 
	schema = "equity")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "devident_id_seq")
	private Long id;
	
	@Include
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name = "share_devident_id_fk",value = ConstraintMode.CONSTRAINT),name = "share_id")
	private Share share;
	
	@Include
	@ColumnDefault("now()")
	@Column(name="deposit_on" ,nullable = false)
	private LocalDateTime depositedOn;
	
	@Column(name="quantity",nullable = false)
	private int quantity;
	
	@Column(name="price",nullable = false,precision = 10,scale = 4)
	private BigDecimal price;

}
