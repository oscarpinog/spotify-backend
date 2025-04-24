package com.seek.codificacion.services.impl;




import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.seek.codificacion.dtos.Cliente;
import com.seek.codificacion.entities.ClienteEntity;
import com.seek.codificacion.exceptions.SuccessfulResponse;
import com.seek.codificacion.repositories.ClienteRepository;
import com.seek.codificacion.services.ClienteService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
//	private final GestionEmpleadosModule gestionEmpleadosModule;
	private final ModelMapper modelMapper = new ModelMapper();
	
    // Claves para acceder a los valores del mapa
    public static final String AGRUPAR = "AGRUPAR";

    @Transactional
	@Override
	public void crearCliente(Cliente cliente) {

		ClienteEntity clienteEntity=modelMapper.map(cliente, ClienteEntity.class);
		clienteRepository.save(clienteEntity);
	}


	@Override
	public List<Cliente> obtenerClientes() {
	    List<Cliente> clientList = clienteRepository.findAll().stream()
	        .map(clienteEntity -> modelMapper.map(clienteEntity, Cliente.class))
	        .collect(Collectors.toList());
	    
	    if (clientList.isEmpty()) {
	    	throw new SuccessfulResponse("No se encontraron registros de clientes.");
		}
	    
	    return clientList;
	}

	
	/**
	 * Obtiene todas las areas por divisionCod
	 * @param divisionCod varaible de entrada de divisiones
	 * @return lista de areas
	 */
//	@Override
//	public List<Area> getAllByDivisionCod(String divisionCod) {
//		gestionEmpleadosModule.actualizarDivisionIsSelec(divisionCod);
//		
//		List<AreaEntity>areaEntityList =areaRepository.spDtAreasDivision();
//		
//		return areaEntityList.stream()
//				.map(map -> modelMapper.map(map, Area.class))
//				.collect(Collectors.toList());
//		
//	}
//
//	/**
//	 * Metodo para obtener valores del area agrupar
//	 * @param areaUsuario valor de ingreso a la consulta
//	 * @return objeto con valores
//	 */
//	@Override
//	public AreaAgrupar getAreaAgrupar(String areaUsuario) {
//
//		Map<String,String> resp=areaRepository.getAreaAgrupar(areaUsuario);
//		
//		return AreaAgrupar.builder()
//				.agrupar(resp.get(AGRUPAR))
//				.descripcionArea(resp.get(DESCRIPCION_AREA))
//				.aprobador1(resp.get(APROBADOR1))
//				.consolidador1(resp.get(CONSOLIDADOR1))
//				.programador1(resp.get(PROGRAMADOR1))
//				.departamento(resp.get(DEPARTAMENTO))
//				.build();
//
//	}
//
//	@Transactional
//	@Override
//	public List<Map<String, Object>> getAllAreas() {
//		List<Map<String, Object>>  list=areaRepository.spConsultarDmAreas();
//		 
//		 return list;
//	}

}
