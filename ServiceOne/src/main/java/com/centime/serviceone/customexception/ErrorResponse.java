package com.centime.serviceone.customexception;

public class ErrorResponse {

    private String message;
    private int status;
    
    public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
    
}
