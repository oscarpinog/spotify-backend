package com.seek.codificacion.services;

import java.util.List;

import com.seek.codificacion.dtos.Cliente;

public interface ClienteService {

	public void crearCliente(Cliente cliente);
	
	public List<Cliente> obtenerClientes();

}
