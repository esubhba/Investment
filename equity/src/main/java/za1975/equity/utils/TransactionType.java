package za1975.equity.utils;

import java.util.HashMap;
import java.util.Map;

public enum TransactionType {

	Sell("S"),Buy("B");
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
