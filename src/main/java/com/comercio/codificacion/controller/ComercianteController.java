package com.comercio.codificacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.codificacion.dtos.ComercianteDto;
import com.comercio.codificacion.services.ComercianteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comerciantes")
public class ComercianteController {

	private final ComercianteService comercianteService;

	@GetMapping("/obtener-comerciantes")
	public ResponseEntity<List<ComercianteDto>> obtenerComerciantes() {
		return new ResponseEntity<>(comercianteService.obtenerComerciantes(), HttpStatus.OK);
	}
	
	@GetMapping("/comerciantes/{id}")
	public ResponseEntity<ComercianteDto> obtenerComerciantePorId(@PathVariable Long id) {
	    return ResponseEntity.ok(comercianteService.obtenerComercianteId(id));
	}

	
	@PostMapping("/crear")
	public ResponseEntity<Void> crearComerciante(@RequestBody @Valid ComercianteDto comerciante) {
		comercianteService.crearComerciante(comerciante);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/actualizar")
	public ResponseEntity<Void> actualizarComerciante(@RequestBody @Valid ComercianteDto comerciante) {
		comercianteService.actualizarComerciante(comerciante);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PostMapping("/borrar/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> borrarComerciante(@PathVariable Long id ) {
		comercianteService.borrarComerciante(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
