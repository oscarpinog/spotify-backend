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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteDerivadoServiceImpl implements ClienteDerivadoService {

    private final ClienteRepository clienteRepository;

    public static final String NO_DATA_CLIENTE = "No se encontraron registros de clientes.";

    @Override
    public List<ClienteDerivado> clientesDerivados() {
        log.info("Iniciando método clientesDerivados");

        List<ClienteEntity> clienteEntityList = clienteRepository.findAll();

        if (clienteEntityList.isEmpty()) {
            log.warn("No se encontraron clientes, lanzando excepción.");
            throw new SuccessfulResponse(NO_DATA_CLIENTE);
        }

        log.info("Mapeando clientes a derivados...");
        List<ClienteDerivado> derivadosList = clienteEntityList.stream()
                .map(cliente -> ClienteDerivado.builder()
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

        log.info("Clientes derivados mapeados exitosamente: {}", derivadosList.size());
        return derivadosList;
    }
}
