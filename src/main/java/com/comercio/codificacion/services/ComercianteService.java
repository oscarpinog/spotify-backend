package com.comercio.codificacion.services;

import java.util.List;

import com.comercio.codificacion.dtos.ComercianteDto;

import jakarta.validation.Valid;

public interface ComercianteService {


	public void borrarComerciante(Long id);

	public void actualizarComerciante(@Valid ComercianteDto comerciante);

	public void crearComerciante(@Valid ComercianteDto comerciante);

	public ComercianteDto obtenerComercianteId(Long id);

	public List<ComercianteDto> obtenerComerciantes();

}
