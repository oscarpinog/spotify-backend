package com.seek.codificacion.services.impl;

import org.springframework.stereotype.Service;

import com.seek.codificacion.repositories.ClienteRepository;
import com.seek.codificacion.services.ClienteService;
import com.seek.codificacion.services.MetricasClienteService;
import com.seek.codificacion.utils.General;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetricasClienteServiceImpl implements MetricasClienteService {

	private final ClienteService clienteService;
	/*
	 * Usamos clienteRepository si queremos desacoplar mas el codigo.
	 */
	private final ClienteRepository clienteRepository;

	// Claves
	public static final String NO_DATA_CLIENTE = "No se encontraron registros de clientes.";

	@Override
	public Double promedioEdad() {

		/* Calcula el promedio de edad de todos los clientes
		 Nota:Si queremos desacoplar el codigo podemos usar clienteRepository para
		 obtener la lista de clientes
		 */
		return clienteService.obtenerClientes().stream()
				.mapToDouble(cliente -> cliente.getEdad())
				.average()
				.orElse(0);

	}

	@Override
	public Double desviacion() {
		/* Nota:Si queremos desacoplar el codigo podemos usar clienteRepository para
		 obtener la lista de clientes
		 */
		return General.calcularDesviacionEstandar(clienteService.obtenerClientes());
	}

}
