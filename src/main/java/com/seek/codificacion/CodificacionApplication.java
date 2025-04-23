package com.seek.codificacion;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodificacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodificacionApplication.class, args);
	}
    @Bean
    CommandLineRunner testConnection(DataSource dataSource) {
        return args -> {
            System.out.println("Conectando con: " + dataSource.getConnection().getMetaData().getURL());
            System.out.println("Driver: " + dataSource.getConnection().getMetaData().getDriverName());
        };
    }

}
