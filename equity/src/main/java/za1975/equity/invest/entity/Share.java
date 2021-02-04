package za1975.equity.invest.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.Getter;
import lombok.Setter;
import za1975.equity.devident.entity.Devident;

@Getter
@Setter
@Entity
@Table(name = "share_details",schema = "equity",uniqueConstraints = @UniqueConstraint(name="share_uq",columnNames = {"code","exn"}))
public class Share extends BasicInformation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8352161598512158585L;

	@Id
	@SequenceGenerator(name = "share_id_seq",
	sequenceName  = "share_id_seq",
	allocationSize = 1,initialValue =1, 
	schema = "equity")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "share_id_seq")
	@Generated(GenerationTime.INSERT)
	private Long id;
	
	@Column(name="details",nullable = true,length = 100)
	private String details;
	
	
	@Column(name="code",nullable = false,length=10)
	private String code;

	@Column(name="exn",nullable = true,length = 10)
	private String exchangeName;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "share",cascade = CascadeType.ALL)	
	private Set<StockDetails> stocks=new HashSet<>();
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "share",cascade = CascadeType.ALL)	
	private Set<Devident> devidents=new HashSet<>();


	@Override
	public int hashCode() {
		return Objects.hash(code, exchangeName, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Share))
			return false;
		Share other = (Share) obj;
		if( Objects.equals(id, other.id))
			return true;
		return Objects.equals(code, other.code) && Objects.equals(exchangeName, other.exchangeName);
	}
	
	

}
