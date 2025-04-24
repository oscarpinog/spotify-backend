package com.seek.codificacion;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seek.codificacion.exceptions.InternalErrorResponse;
import com.seek.codificacion.security.Usuario;
import com.seek.codificacion.security.UsuarioRepository;

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
    @Bean
    public CommandLineRunner crearUsuarioPorDefecto(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setUsername("admin");
                usuario.setPassword(encoder.encode("admin123")); 
                usuario.setRol("ROLE_ADMIN");//ROLE_USER

                usuarioRepository.save(usuario);

            } else {
            	throw new InternalErrorResponse("Usuario admin ya existe. No se creó otro.");
            }
        };
    }


}
