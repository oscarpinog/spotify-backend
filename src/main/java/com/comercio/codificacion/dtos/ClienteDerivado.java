package com.comercio.codificacion.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteDerivado implements Serializable{

	private static final long serialVersionUID = 2764238622649305869L;
    private Long id;
    
    @NotBlank(message ="El nombre no puede estar vacio")
	private String nombre;
    
    @NotBlank(message ="El apellido no puede estar vacio")
	private String apellido;
    
    @NotNull(message ="Debe ingresar una edad")
    private Integer edad;
    
    @NotNull(message = "La fecha de nacimiento es obligatoria")
	private LocalDate fechaNacimiento;
    
    private long diasProxCumpleanos;
    private long edadMeses;
    private int anosHastaJubilacion;
    

}
