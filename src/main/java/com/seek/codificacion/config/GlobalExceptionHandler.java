package com.seek.codificacion.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Metodo que retorna errores de Validators
	 * @param ex
	 * @return Mensaje y status de error personalizado
	 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        
    	ex.getBody().getStatus();
//    	si es 400 unprocessable
//    	si es 500 Internal
    	
    	List<String> errores = ex.getBindingResult().getAllErrors().stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());
        return ResponseEntity.unprocessableEntity().body(errores);
    }
}
