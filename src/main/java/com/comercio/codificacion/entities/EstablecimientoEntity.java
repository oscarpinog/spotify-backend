package com.comercio.codificacion.entities;



import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Table(name = "establecimiento")
public class EstablecimientoEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nombre;

	    @Column(precision = 12, scale = 2)
	    private BigDecimal ingresos;

	    private Integer numeroEmpleados;

	    @ManyToOne
	    @JoinColumn(name = "comerciante_id")
	    private ComercianteEntity comerciante;

	    private LocalDateTime fechaActualizacion;

	    @ManyToOne
	    @JoinColumn(name = "usuario_actualizacion_id")
	    private UsuarioEntity usuarioActualizacion;
}
