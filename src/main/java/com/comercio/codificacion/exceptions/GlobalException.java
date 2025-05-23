package com.comercio.codificacion.exceptions;

import java.util.Date;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(1)
public class GlobalException extends Exception {

	private static final long serialVersionUID = 2050529033439513271L;

	@ExceptionHandler
	public ResponseEntity<ErrorObject> notFoundGenericExeption(NotFoundResponse ex, HandlerMethod handlerMethod,
			HttpServletRequest request) {
		ErrorObject error = new ErrorObject();
		Class<?> nombreControlador = handlerMethod.getMethod().getDeclaringClass();
		String nombreMetodo = handlerMethod.getMethod().getName();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		error.setMessage(ex.getMessage());
		error.setTime(new Date());
		error.setPath(request.getRequestURL().toString());
		error.setControllerName(nombreControlador.toString());
		error.setServiceName(nombreMetodo);

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorObject> SuccessfulGenericExeption(SuccessfulResponse ex, HandlerMethod handlerMethod,
			HttpServletRequest request) {
		ErrorObject error = new ErrorObject();
		Class<?> nombreControlador = handlerMethod.getMethod().getDeclaringClass();
		String nombreMetodo = handlerMethod.getMethod().getName();
		error.setStatusCode(HttpStatus.OK.value());
		error.setHttpStatus(HttpStatus.OK.toString());
		error.setMessage(ex.getMessage());
		error.setTime(new Date());
		error.setPath(request.getRequestURL().toString());
		error.setControllerName(nombreControlador.toString());
		error.setServiceName(nombreMetodo);

		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorObject> internalErrorGenericExeption(InternalErrorResponse ex,
			HandlerMethod handlerMethod, HttpServletRequest request) {
		ErrorObject error = new ErrorObject();
		Class<?> nombreControlador = handlerMethod.getMethod().getDeclaringClass();
		String nombreMetodo = handlerMethod.getMethod().getName();
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		error.setMessage(ex.getMessage());
		error.setTime(new Date());
		error.setPath(request.getRequestURL().toString());
		error.setControllerName(nombreControlador.toString());
		error.setServiceName(nombreMetodo);

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorObject> ResponseStatusException(ResponseStatusException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
		
		ErrorObject error = new ErrorObject();
		Class<?> nombreControlador = handlerMethod.getMethod().getDeclaringClass();
		String nombreMetodo = handlerMethod.getMethod().getName();
		error.setStatusCode(ex.getStatusCode().value());
		error.setHttpStatus(ex.getStatusCode().toString());
		error.setMessage(ex.getMessage());
		error.setTime(new Date());
		error.setPath(request.getRequestURL().toString());
		error.setControllerName(nombreControlador.toString());
		error.setServiceName(nombreMetodo);
		
		return new ResponseEntity<>(error, ex.getStatusCode());
	}
	
}
