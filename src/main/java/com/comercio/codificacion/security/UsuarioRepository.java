package com.comercio.codificacion.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comercio.codificacion.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}