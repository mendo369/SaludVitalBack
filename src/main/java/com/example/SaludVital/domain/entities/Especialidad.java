package com.example.SaludVital.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "especialidades")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecialidad;

    @Column(name = "nombre_especialidad", nullable = false, unique = true)
    private String nombreEspecialidad;

    private String descripcion;
}