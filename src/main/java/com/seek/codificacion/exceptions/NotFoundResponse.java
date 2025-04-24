package com.seek.codificacion.exceptions;

public class NotFoundResponse extends RuntimeException{

	private static final long serialVersionUID = 1376705085425697980L;

	public NotFoundResponse(String message){
	    super(message);
	}

}
