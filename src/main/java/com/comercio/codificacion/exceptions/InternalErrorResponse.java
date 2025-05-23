package com.comercio.codificacion.exceptions;

public class InternalErrorResponse extends RuntimeException{

	private static final long serialVersionUID = 1376705085425697980L;

	public InternalErrorResponse(String message){
	    super(message);
	}

}
