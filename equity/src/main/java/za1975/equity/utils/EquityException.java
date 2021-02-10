package za1975.equity.utils;

import java.net.URI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6565083490952403375L;
	String title;
	String message;
	URI uri;
	public EquityException(String title, String message, URI uri) {
		super(title);
		this.title = title;
		this.message = message;
		this.uri = uri;
	}
	
	
	
}
