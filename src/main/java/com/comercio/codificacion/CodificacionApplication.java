package com.comercio.codificacion;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.comercio.codificacion.entities.UsuarioEntity;
import com.comercio.codificacion.exceptions.InternalErrorResponse;
import com.comercio.codificacion.security.UsuarioRepository;


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

                UsuarioEntity usuario = new UsuarioEntity();
                usuario.setNombre("Oscar Rodriguez");
                usuario.setCorreoElectronico("oscarrodriguez@olsofware.com");
                usuario.setUsername("admin");
                usuario.setPassword(encoder.encode("admin123")); 
                usuario.setRol("ROLE_ADMIN");

                usuarioRepository.save(usuario);
                
                UsuarioEntity usuarioUser = new UsuarioEntity();
                usuarioUser.setNombre("Gerardo Pino");
                usuarioUser.setCorreoElectronico("gerardopino@olsofware2.com");
                usuarioUser.setUsername("aux");
                usuarioUser.setPassword(encoder.encode("aux123")); 
                usuarioUser.setRol("ROLE_AUX");

                usuarioRepository.save(usuarioUser);

        };
    }


}
