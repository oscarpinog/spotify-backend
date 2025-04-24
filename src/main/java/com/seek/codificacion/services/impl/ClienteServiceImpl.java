package com.seek.codificacion.services.impl;




import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.seek.codificacion.dtos.Cliente;
import com.seek.codificacion.entities.ClienteEntity;
import com.seek.codificacion.repositories.ClienteRepository;
import com.seek.codificacion.services.ClienteService;
import com.seek.codificacion.utils.General;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	
    // Claves
    public static final String NO_DATA_CLIENTE = "No se encontraron registros de clientes.";

    @Transactional
	@Override
	public void crearCliente(Cliente cliente) {

		ClienteEntity clienteEntity=modelMapper.map(cliente, ClienteEntity.class);
		clienteEntity.setEdad(General.calcularEdad(cliente.getFechaNacimiento()));
		
		clienteRepository.save(clienteEntity);
	}


}
