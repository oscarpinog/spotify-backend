package com.comercio.codificacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.codificacion.dtos.ClienteDerivado;
import com.comercio.codificacion.services.ClienteDerivadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/derivado")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class ClienteDerivadoController {

	private final ClienteDerivadoService clienteDerivadoService;

	@GetMapping("/cliente-derivado")
	public ResponseEntity<List<ClienteDerivado>> clientesDerivados() {
		return new ResponseEntity<>(clienteDerivadoService.clientesDerivados(), HttpStatus.OK);
	}



}
