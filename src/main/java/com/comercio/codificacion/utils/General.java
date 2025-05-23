package com.comercio.codificacion.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.comercio.codificacion.entities.ClienteEntity;
import com.comercio.codificacion.exceptions.SuccessfulResponse;

public class General {

    // Constantes
	public static final int EDAD_JUBILACION = 65;
	
	public static double calcularDesviacionEstandar(List<ClienteEntity> clientes) {
	    
	    if (clientes.size()<=0) {
			throw new SuccessfulResponse("No es posible calcular desviacion porque no existen clientes");
		}

	    // 1. Calcular promedio
	    double promedio = clientes.stream()
	        .mapToInt(ClienteEntity::getEdad)
	        .average()
	        .orElse(0);

	    // 2. Calcular la suma de los cuadrados de las diferencias respecto al promedio
	    double sumaCuadrados = clientes.stream()
	        .mapToDouble(c -> Math.pow(c.getEdad() - promedio, 2))
	        .sum();

	    // 3. Calcular desviación estándar
	    return Math.sqrt(sumaCuadrados / clientes.size());
	}

    /**
     * Días para su próximo cumpleaños
     */
    public static long diasParaProximoCumple(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        LocalDate proximoCumple = fechaNacimiento.withYear(hoy.getYear());

        if (!proximoCumple.isAfter(hoy)) {
            proximoCumple = proximoCumple.plusYears(1);
        }

        return ChronoUnit.DAYS.between(hoy, proximoCumple);
    }

    /**
     * Edad en meses
     */
    public static long edadEnMeses(LocalDate fechaNacimiento) {
        return ChronoUnit.MONTHS.between(fechaNacimiento, LocalDate.now());
    }

    /**
     * Años hasta la jubilación
     */
    public static int anosHastaJubilacion(LocalDate fechaNacimiento) {
        int edadActual = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        int añosRestantes = EDAD_JUBILACION - edadActual;
        return Math.max(añosRestantes, 0);
    }
    
    public static int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }

}
