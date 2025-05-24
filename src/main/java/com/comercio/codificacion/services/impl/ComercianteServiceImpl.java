package com.comercio.codificacion.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.comercio.codificacion.dtos.ComercianteDto;
import com.comercio.codificacion.entities.ComercianteEntity;
import com.comercio.codificacion.exceptions.NotFoundResponse;
import com.comercio.codificacion.exceptions.SuccessfulResponse;
import com.comercio.codificacion.repositories.ComercianteRepository;
import com.comercio.codificacion.services.ComercianteService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComercianteServiceImpl implements ComercianteService {

	private final ComercianteRepository comercianteRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public static final String NO_DATA = "No se encontraron registros de Comerciante.";

    @Transactional
    @Override
    public void crearComerciante(ComercianteDto comerciante) {
        log.info("Iniciando método Comerciante");

        ComercianteEntity comercianteEntity = modelMapper.map(comerciante, ComercianteEntity.class);
        log.info("Comerciante mapeado a entidad: {}", comercianteEntity);

        comercianteRepository.save(comercianteEntity);
        log.info("Comerciante guardado exitosamente en la base de datos");
    }

    @Transactional
	@Override
	public void borrarComerciante(Long id) {
		// TODO Auto-generated method stub
		if(Objects.isNull(id)) {
			throw new NotFoundResponse(NO_DATA);
		}
		comercianteRepository.deleteById(id);
	}

    @Transactional
	@Override
	public void actualizarComerciante(ComercianteDto comerciante) {
		// TODO Auto-generated method stub
    	Optional<ComercianteEntity> comercianteEntity=comercianteRepository.findById(comerciante.getId());
    	
    	if(!comercianteEntity.isPresent()) {
    		throw new SuccessfulResponse(NO_DATA);
    	}
    	  ComercianteEntity comercianteSaveEntity = modelMapper.map(comerciante, ComercianteEntity.class);
    	comercianteRepository.save(comercianteSaveEntity);
		
	}

	@Override
	public ComercianteDto obtenerComercianteId(Long id) {
		// TODO Auto-generated method stub
		Optional<ComercianteEntity> comercianteEntity=comercianteRepository.findById(id);
		
		ComercianteDto comercianteDto = modelMapper.map(comercianteEntity.get(), ComercianteDto.class);
		
		return comercianteDto;
	}

	@Override
	public List<ComercianteDto> obtenerComerciantes() {
		// TODO Auto-generated method stub
		List<ComercianteEntity> entidades = comercianteRepository.findAll();
	    if (entidades.isEmpty()) {
	        log.info("No se encontraron comerciantes.");
	        throw new NotFoundResponse(NO_DATA);
	    }
		
	    return entidades.stream()
	            .map(entity -> modelMapper.map(entity, ComercianteDto.class))
	            .collect(Collectors.toList());

	}
}
