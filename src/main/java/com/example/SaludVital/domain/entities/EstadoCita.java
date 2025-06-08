package com.example.SaludVital.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estados_cita")
public class EstadoCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;

    @Column(name = "nombre_estado", nullable = false, unique = true)
    private String nombreEstado;

    private String descripcion;
}