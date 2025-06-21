package com.example.SaludVital.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "servicios_adicionales")
public class ServicioAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicio;

    @Column(name = "nombre_servicio", nullable = false, unique = true)
    private String nombreServicio;

    @Column(nullable = false)
    private Double costo;

    private String descripcion;

    private Boolean activo;
}