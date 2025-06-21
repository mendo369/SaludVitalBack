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
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private ServicioAdicional servicio;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;

    @Column(name = "fecha_servicio", nullable = false)
    private LocalDateTime fechaServicio;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "costo_unitario", nullable = false)
    private Double costoUnitario;

    @Column(name = "costo_total", nullable = false)
    private Double costoTotal;

    private String observaciones;
}