package com.spotify.codificacion.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDto implements Serializable{

	private static final long serialVersionUID = 2764238622649305869L;
	
	private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private Rol rol;

}
