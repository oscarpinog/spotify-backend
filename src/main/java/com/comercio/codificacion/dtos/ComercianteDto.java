package com.comercio.codificacion.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComercianteDto implements Serializable{

	private static final long serialVersionUID = 2764238622649305869L;
	
    private Long id;
    private String nombreRazonSocial;
    private String municipio;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;
    private boolean estado;
    private LocalDateTime fechaActualizacion;
    private UsuarioDto usuarioActualizacion;

}
