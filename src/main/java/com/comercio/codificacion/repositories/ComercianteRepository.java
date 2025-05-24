package com.comercio.codificacion.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comercio.codificacion.entities.ComercianteEntity;


@Repository
public interface ComercianteRepository extends JpaRepository<ComercianteEntity, Long> {
	
	
	
//	public List<MonedaEntity> findByEstadoOrderByAbreviacionAsc(Boolean estado);
//	
//	public MonedaEntity findByAbreviacion(String abreviacion);
//	
//	public MonedaEntity findByAbreviacionAndEstado(String abreviacion, Boolean estado);
//	
//	public MonedaEntity findByIdAndEstado(Integer id, Boolean estado);
	
}
