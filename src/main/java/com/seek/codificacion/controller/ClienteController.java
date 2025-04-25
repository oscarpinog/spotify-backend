package com.seek.codificacion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seek.codificacion.dtos.Cliente;
import com.seek.codificacion.services.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteService clienteService;

	@PostMapping("/crear")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> crearCliente(@RequestBody @Valid Cliente cliente) {
		clienteService.crearCliente(cliente);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
