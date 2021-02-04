package za1975.equity.invest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("error_details")
public class EquityException  {
	
	@JsonProperty("error_code")
	private String errorCode;
	@JsonProperty("message")
	private String message;
	
	

}
