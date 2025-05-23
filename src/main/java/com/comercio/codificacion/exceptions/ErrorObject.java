package com.comercio.codificacion.exceptions;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorObject {
	@JsonFormat(timezone="America/Bogota")
	private Date time;
	private Integer statusCode;
	private String httpStatus;
	private String message;
	private String path;
	private String serviceName;
	private String controllerName;
}
