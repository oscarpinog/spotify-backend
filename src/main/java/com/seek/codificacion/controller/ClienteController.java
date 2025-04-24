package com.seek.codificacion.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seek.codificacion.dtos.Cliente;
import com.seek.codificacion.services.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente") 
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@PostMapping("/crear")
  public ResponseEntity<Void> crearCliente(@RequestBody Cliente cliente) {
		clienteService.crearCliente(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
  }
	
	@GetMapping("/obtener")
	  public ResponseEntity<List<Cliente>> obtenerClientes(@RequestBody Cliente cliente) {
			return new ResponseEntity<>(clienteService.obtenerClientes(),HttpStatus.OK);
	  }
	
//	private final EmpleadoService empleadoService;
//	
//	@GetMapping("/buscar-retirado/{retirado}/{divisionCod}")
//    public ResponseEntity<List<EmpleadoNombreCedula>> getAllByIsRetiredAndDivision(@PathVariable() Integer retirado,@PathVariable() String divisionCod) {
//        return new ResponseEntity<>(empleadoService.getAllByIsRetiredAndDivision(retirado,divisionCod), HttpStatus.OK);
//    }
//	
//	@GetMapping("/buscar-documento/{numDocumento}")
//    public ResponseEntity<Empleado> getAllByNumDoc(@PathVariable() String numDocumento) {
//        return new ResponseEntity<>(empleadoService.getAllByNumDoc(numDocumento), HttpStatus.OK);
//    }
//	
//	@PutMapping("/actualizar")
//    public ResponseEntity<Void> update(@RequestBody Empleado empleado) {
//    	empleadoService.update(empleado);
//    	return new ResponseEntity<>(HttpStatus.OK);
//    }
//	
//	@PostMapping("/buscar/fitros")
//    public ResponseEntity<List<Map<String,Object>>> getAllEmpleadosByFitrosPhre(@RequestBody EmpleadoFitro empleadoFitros) {
//    	return new ResponseEntity<>(empleadoService.getAllEmpleadosByFitros(empleadoFitros),HttpStatus.OK);
//    }
//	@PostMapping("/actualizar/division")
//    public ResponseEntity<Map<String, String>> actualizarEmpleadoDivision(@RequestBody EmpleadoDivision empleadoDivision) {
//    	return new ResponseEntity<>(empleadoService.actualizarEmpleadoDivision(empleadoDivision),HttpStatus.OK);
//    }
	
}
