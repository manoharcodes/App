package com.interview.manohar.exception;

public class SynonymException extends Exception{
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public SynonymException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	SynonymException(){
		super();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

}
