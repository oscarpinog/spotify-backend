package com.comercio.codificacion.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.comercio.codificacion.dtos.Cliente;
import com.comercio.codificacion.entities.ClienteEntity;
import com.comercio.codificacion.repositories.ClienteRepository;
import com.comercio.codificacion.services.ClienteService;
import com.comercio.codificacion.utils.General;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public static final String NO_DATA_CLIENTE = "No se encontraron registros de clientes.";

    @Transactional
    @Override
    public void crearCliente(Cliente cliente) {
        log.info("Iniciando método crearCliente");

        String fecha = cliente.getFechaNacimiento().toString();

        ClienteEntity clienteEntity = modelMapper.map(cliente, ClienteEntity.class);
        log.info("Cliente mapeado a entidad: {}", clienteEntity);

        clienteEntity.setEdad(General.calcularEdad(cliente.getFechaNacimiento()));

        clienteRepository.save(clienteEntity);
        log.info("Cliente guardado exitosamente en la base de datos");
    }
}
