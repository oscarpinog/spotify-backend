package com.seek.codificacion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seek.codificacion.services.MetricasClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metrica")
public class MetricasClienteController {

	private final MetricasClienteService metricasService;

	@GetMapping("/promedio-edad")
	public ResponseEntity<Double> promedioEdad() {
		return new ResponseEntity<>(metricasService.promedioEdad(), HttpStatus.OK);
	}

	@GetMapping("/desviacion")
	public ResponseEntity<Double> desviacion() {
		return new ResponseEntity<>(metricasService.desviacion(), HttpStatus.OK);
	}

}
