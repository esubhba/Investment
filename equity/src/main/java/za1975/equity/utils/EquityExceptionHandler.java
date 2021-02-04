package za1975.equity.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import za1975.equity.invest.model.EquityException;

@ControllerAdvice
public class EquityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleExceptionInternal(NullPointerException e,  WebRequest request) {		
		EquityException ex=new EquityException();
		ex.setErrorCode("N001");
		ex.setMessage(messageSource.getMessage("N001", new String[] {e.getMessage()}, Locale.US));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
	}
	
	
}
