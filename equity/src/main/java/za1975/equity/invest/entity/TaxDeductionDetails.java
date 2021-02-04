package za1975.equity.invest.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.Getter;
import lombok.Setter;
import za1975.equity.utils.TaxType;

@Getter
@Setter
@Entity
@Table(name="tax_deduction_details",schema="equity")
public class TaxDeductionDetails extends BasicInformation {

	
	private static final long serialVersionUID = -4457654372457930777L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Generated(GenerationTime.INSERT)
	private Integer id;
	
	@Column(name="tax_name",nullable = false)
	private String taxName;
	
	@Column(name="tax_type",nullable=false,length = 1)
	@Enumerated(EnumType.STRING)
	private TaxType type;
	
	
	@Column(name="deducted_on")
	private LocalDateTime deductedOn;

	@Override
	public int hashCode() {
		return Objects.hash(deductedOn, id, taxName, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TaxDeductionDetails))
			return false;
		TaxDeductionDetails other = (TaxDeductionDetails) obj;
		if(Objects.equals(id, other.id)) return true;
		return Objects.equals(deductedOn, other.deductedOn)  
				&& Objects.equals(taxName, other.taxName) && type == other.type;
	}
	
	
}
