package com.comercio.codificacion.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstablecimientoDto implements Serializable{

	private static final long serialVersionUID = 2764238622649305869L;
	
    private Long id;
    private String nombre;
    private BigDecimal ingresos;
    private Integer numeroEmpleados;
    private Long comercianteId;
    private LocalDateTime fechaActualizacion;
    private UsuarioDto usuarioActualizacion;

}
