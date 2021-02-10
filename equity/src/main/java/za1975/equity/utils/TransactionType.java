package za1975.equity.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = Shape.OBJECT)
public enum TransactionType {

	@JsonProperty("sell") Sell("S"),
	@JsonProperty("buy")  Buy("B");
	private static final Map<String,TransactionType>map=new HashMap<>();
	static {
		map.put("S", Sell);
		map.put("B", Buy);
	}
	private TransactionType(String value) {
		this.value=value;
	}
	
	
	private String value;


	public String getValue() {
		return value;
	}
	
	
	
}
