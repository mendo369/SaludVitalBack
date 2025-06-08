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

    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "id_servicio")
    private Integer idServicio;

    @Column(name = "id_cita")
    private Integer idCita;

    @Column(name = "fecha_servicio")
    private LocalDateTime fechaServicio;

    private Integer cantidad;

    @Column(name = "costo_unitario")
    private Double costoUnitario;

    @Column(name = "costo_total")
    private Double costoTotal;

    private String observaciones;
}