package com.seek.codificacion.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



public class General {

	
	private static DateTimeFormatter dateTimeFormatterddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter dateTimeFormatterHHmmss = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter dateTimeFormatterHHmm = DateTimeFormatter.ofPattern("HH:mm");
	
	
	public String organizarFecha(LocalDateTime fecha) {
		try {
			String fechaAjustada;
			
			Integer dia = fecha.getDayOfMonth();
			Integer mes = fecha.getMonth().getValue();
			Integer anho = fecha.getYear();
			
			fechaAjustada = (dia < 10 ? "0"+dia : dia)+"/"+(mes < 10 ? "0"+mes : mes)+"/"+anho;
					
			return fechaAjustada;
		} catch (Exception e) {
			throw new RuntimeException("Error: "+e.getMessage());
		}
		
	}
	


	public String convertirFecha(LocalDateTime fechaIngreso) {
		// Extraemos la parte de la fecha (sin la hora) y la formateamos

		return fechaIngreso.toLocalDate().format(dateTimeFormatterddMMyyyy);
	}
	
	 // Método que recibe un LocalDate y devuelve la fecha en formato dd/MM/yyyy
    public static String convertirFechaAString(LocalDate fecha) {
        // Definir el formato deseado (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertir el LocalDate a String con el nuevo formato
        return fecha.format(formatter);
    }
	
	public String convertirHora(LocalTime hora) {
//		Extraemos la parte de la Hora y la formateamos

		return hora.format(dateTimeFormatterHHmmss);
	}
	//Pasa un string a local date 
	public LocalDate convertirFechaALocalDate(String fechaStr) {
		
		 return LocalDate.parse(fechaStr, dateTimeFormatterddMMyyyy);
		
	}
	
	   // Método para agregar días a una fecha
    public static LocalDate agregarDias(LocalDate fecha, int dias) {
        return fecha.plusDays(dias);  // Suma los días especificados
    }
    
    /**
     * Metodo para pasar de String a tipo LocalTime 
     */
    public LocalTime convHoraLocalTime (String horaString) {
		
    	return  LocalTime.parse(horaString, dateTimeFormatterHHmm);
    	
    }
    
	public static String obtenerValor(Object valor) {
    	Object valorObj = valor.toString();
		if (valorObj instanceof Integer) {
			return  (String) valorObj;
		}else if(valorObj instanceof String) {
			return (String) valorObj;
		}else if (valorObj instanceof BigDecimal) {
			return ((BigDecimal) valorObj).toString();
		}else { // Manejar otros casos según sea necesario
			return valorObj.toString();
		}
	}
	
	// Método para calcular el porcentaje
    public static String calcularPorcentaje(double parte, double total) {
        if (total == 0) {
            return "0.00"; // Evitar la división por cero y devolver "0.00" como String
        }
        double resultado = (parte / total) * 100;
        // Formatear el resultado para mostrar siempre 2 decimales
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(resultado) + "%"; // Devolver el valor como String con 2 decimales
    }
    
    public static String ultimoDiaDelMes(int ano, int mes) {
        // Crear una fecha con el primer día del mes
        LocalDate primerDiaDelMes = LocalDate.of(ano, mes, 1);

        // Obtener el último día del mes
        LocalDate ultimoDiaDelMes = primerDiaDelMes.withDayOfMonth(primerDiaDelMes.lengthOfMonth());

        // Formatear la fecha a String con el formato especificado
        return ultimoDiaDelMes.format(dateTimeFormatterddMMyyyy);
    }
}
