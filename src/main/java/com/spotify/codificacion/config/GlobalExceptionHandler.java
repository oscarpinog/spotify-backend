package com.spotify.codificacion.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(2)
public class GlobalExceptionHandler {

	
	/**
	 * Metodo que retorna errores de Validators
	 * @param ex
	 * @return Mensaje y status de error personalizado
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Map<String, String>> manejarUsuarioNoEncontrado(UsernameNotFoundException ex) {
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Usuario no encontrado");
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Map<String, String>> manejarCredencialesInvalidas(BadCredentialsException ex) {
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Credenciales incorrectas");
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> manejarErroresGenerales(Exception ex) {
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Ocurrió un error inesperado");
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException ex) {
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Error: Usuario no autenticado. Por favor, envía un token válido.");
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Map<String, String>> manejarAccesoDenegado(AccessDeniedException ex) {
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "No tenés permisos para acceder a este recurso.");
	    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

}
