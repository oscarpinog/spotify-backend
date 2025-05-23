package com.comercio.codificacion.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente implements Serializable{

	private static final long serialVersionUID = 2764238622649305869L;
	
    @NotBlank(message ="El nombre no puede estar vacio")
	private String nombre;
    
    @NotBlank(message ="El apellido no puede estar vacio")
	private String apellido;
    
	private LocalDate fechaNacimiento;

}
