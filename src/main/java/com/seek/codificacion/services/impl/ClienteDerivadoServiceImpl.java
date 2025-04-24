package com.seek.codificacion.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.seek.codificacion.dtos.ClienteDerivado;
import com.seek.codificacion.entities.ClienteEntity;
import com.seek.codificacion.exceptions.SuccessfulResponse;
import com.seek.codificacion.repositories.ClienteRepository;
import com.seek.codificacion.services.ClienteDerivadoService;
import com.seek.codificacion.utils.General;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteDerivadoServiceImpl implements ClienteDerivadoService {


	private final ClienteRepository clienteRepository;

	// Claves
	public static final String NO_DATA_CLIENTE = "No se encontraron registros de clientes.";

	@Override
	public List<ClienteDerivado> clientesDerivados() {

		List<ClienteEntity> clienteEntityList= clienteRepository.findAll();
		if (clienteEntityList.isEmpty()) {
			throw new SuccessfulResponse(NO_DATA_CLIENTE);
		}
		
		List<ClienteDerivado> derivadosList = clienteEntityList.stream()
			    .map(cliente -> 
			    ClienteDerivado.builder()
			    			   .id(cliente.getId())
			                   .nombre(cliente.getNombre())
			                   .apellido(cliente.getApellido())
			                   .edad(cliente.getEdad())
			                   .fechaNacimiento(cliente.getFechaNacimiento())
			                   .diasProxCumpleanos(General.diasParaProximoCumple(cliente.getFechaNacimiento()))         
			                   .edadMeses(General.edadEnMeses(cliente.getFechaNacimiento()))
			                   .anosHastaJubilacion(General.anosHastaJubilacion(cliente.getFechaNacimiento()))
			                   .build())
			    .collect(Collectors.toList());
		
		
		return derivadosList;
	}

	

}
