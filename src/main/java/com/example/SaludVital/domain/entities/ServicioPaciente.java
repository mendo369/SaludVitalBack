package com.example.SaludVital.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "servicios_paciente")
public class ServicioPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicioPaciente;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private ServicioAdicional servicioAdicional;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "id_paciente", nullable = false)
    private Integer idPaciente;

    @Column(nullable = false)
    private LocalDateTime fechaServicio;

    @Column(nullable = false)
    private Double costoUnitario;

    @Column(nullable = false)
    private Double costoTotal;

    @Column(length = 255)
    private String observaciones;
}