package com.example.SaludVital.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_tipo_consulta")
    private TipoConsulta tipoConsulta;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoCita estadoCita;

    @Column(name = "fecha_cita", nullable = false)
    private LocalDate fechaCita;

    @Column(name = "hora_cita", nullable = false)
    private LocalTime horaCita;

    private String observaciones;

    @Column(name = "costo_consulta", nullable = false)
    private Double costoConsulta;

    @Column(name = "descuento_aplicado")
    private Double descuentoAplicado;

    @Column(name = "costo_total", nullable = false)
    private Double costoTotal;

    @ManyToOne
    @JoinColumn(name = "id_usuario_registro")
    private Usuario usuarioRegistro;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "hora_llegada")
    private LocalDateTime horaLlegada;
}