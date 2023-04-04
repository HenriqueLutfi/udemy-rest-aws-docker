package br.com.lutfi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // status a responder quando der esse erro
public class MathOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MathOperationException(String ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}

}
