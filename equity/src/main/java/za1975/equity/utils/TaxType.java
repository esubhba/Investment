package za1975.equity.utils;

import java.util.HashMap;
import java.util.Map;

public enum TaxType {

	Monthly("M"),Yearly("Y"),Daily("D");
	
	private String value;
	
	private TaxType(String value)
	{
		this.value=value;
	}
	private static final Map<String,TaxType>map=new HashMap<>();
	static {
		map.put("M", Monthly);
		map.put("Y", Yearly);
	}
	public String getValue() {
		return value;
	}
	public static TaxType getByValue(String value)
	{
		return map.get(value.toUpperCase());
	}
}
