package com.seek.codificacion.dtos;



import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente implements Serializable{

	private static final long serialVersionUID = 2764238622649305869L;
    private Long id;
	private String nombre;
	private String apellido;
    private Integer edad;
	private LocalDate fechaNacimiento;

}
