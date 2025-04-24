package com.seek.codificacion.exceptions;

public class SuccessfulResponse extends RuntimeException{

	private static final long serialVersionUID = 1376705085425697980L;

	public SuccessfulResponse(String message){
	    super(message);
	}

}
