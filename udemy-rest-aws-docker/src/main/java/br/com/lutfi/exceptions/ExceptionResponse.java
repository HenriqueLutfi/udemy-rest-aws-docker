package br.com.lutfi.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timeStamp;
	private String Message;
	private String details;

	public ExceptionResponse(Date timeStamp, String message, String details) {
		this.timeStamp = timeStamp;
		Message = message;
		this.details = details;
	}

	public ExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
