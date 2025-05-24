package com.comercio.codificacion.entities;



import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comerciante")
public class ComercianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreRazonSocial;

    private String municipio;

    private String telefono;

    private String correoElectronico;

    private LocalDate fechaRegistro;

    private boolean estado; 

    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "usuario_actualizacion_id")
    private UsuarioEntity usuarioActualizacion;
}
