/**
 * 
 */
package za1975.equity.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SUBHADEEP
 *
 */
public enum DepositType {
	@JsonProperty("Cr.") CR ("Cr"), @JsonProperty("Dr.") DR("Dr");
	private static final Map<String,DepositType>map=new HashMap<>();
	static {
		map.put("Cr", CR);
		map.put("Dr", DR);
	}
	private String value;
	
	DepositType(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
	public static DepositType getByValue(String value)
	{
		return map.get(value);
	}
	
}
