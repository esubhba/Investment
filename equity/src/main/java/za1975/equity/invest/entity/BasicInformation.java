package za1975.equity.invest.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public abstract class BasicInformation implements Serializable {

	/**
	 *  static serialVersionUID
	 */
	private static final long serialVersionUID = 9212325906080406345L;

	@Column(name="created_by",nullable = false,length = 250)
	private String createdBy;
	
	@Column(name = "created_on",nullable = false,
			updatable = false)
	@CreatedDate
	private LocalDateTime createdOn;
	
	@Column(name = "modified_by",nullable = true,length = 250 )
	private String modifiedBy;
	
	@ColumnDefault(value = "now()")
	@Column(name="modified_on",nullable = true)
	private LocalDateTime modifiedOn;
}
