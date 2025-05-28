package com.spotify.codificacion.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.codificacion.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}