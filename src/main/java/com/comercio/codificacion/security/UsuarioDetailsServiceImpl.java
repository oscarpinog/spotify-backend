package com.comercio.codificacion.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.comercio.codificacion.entities.UsuarioEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Iniciando carga de detalles para el usuario: {}", username);

		UsuarioEntity usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> {
			log.error("Usuario no encontrado: {}", username);
			return new UsernameNotFoundException("Usuario no encontrado");
		});

		log.info("Usuario encontrado: {}", username);
		return new User(usuario.getUsername(), usuario.getPassword(),
				List.of(new SimpleGrantedAuthority(usuario.getRol())));
	}
}
