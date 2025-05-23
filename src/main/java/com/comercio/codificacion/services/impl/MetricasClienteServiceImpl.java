package com.comercio.codificacion.services.impl;

import org.springframework.stereotype.Service;

import com.comercio.codificacion.repositories.ClienteRepository;
import com.comercio.codificacion.services.MetricasClienteService;
import com.comercio.codificacion.utils.General;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricasClienteServiceImpl implements MetricasClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Double promedioEdad() {
        log.info("Iniciando cálculo de promedio de edad de los clientes");

        Double promedio = clienteRepository.findAll().stream()
                .mapToDouble(cliente -> cliente.getEdad())
                .average()
                .orElse(0);

        log.info("Promedio de edad calculado: {}", promedio);
        return promedio;
    }

    @Override
    public Double desviacion() {
        log.info("Iniciando cálculo de la desviación estándar de la edad de los clientes");

        Double desviacion = General.calcularDesviacionEstandar(clienteRepository.findAll());

        log.info("Desviación estándar calculada: {}", desviacion);
        return desviacion;
    }
}
