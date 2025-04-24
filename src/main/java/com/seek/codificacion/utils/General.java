package com.seek.codificacion.utils;

import java.util.List;

import com.seek.codificacion.dtos.Cliente;
import com.seek.codificacion.exceptions.SuccessfulResponse;

public class General {

	
	public static double calcularDesviacionEstandar(List<Cliente> clientes) {
	    
	    if (clientes.size()<=0) {
			throw new SuccessfulResponse("No es posible calcular desviacion porque no existen clientes");
		}

	    // 1. Calcular promedio
	    double promedio = clientes.stream()
	        .mapToInt(Cliente::getEdad)
	        .average()
	        .orElse(0);

	    // 2. Calcular la suma de los cuadrados de las diferencias respecto al promedio
	    double sumaCuadrados = clientes.stream()
	        .mapToDouble(c -> Math.pow(c.getEdad() - promedio, 2))
	        .sum();

	    // 3. Calcular desviación estándar
	    return Math.sqrt(sumaCuadrados / clientes.size());
	}

}
