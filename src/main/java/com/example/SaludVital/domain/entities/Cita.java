package com.example.SaludVital.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "id_medico")
    private Integer idMedico;

    @Column(name = "id_tipo_consulta")
    private Integer idTipoConsulta;

    @Column(name = "fecha_cita")
    private LocalDate fechaCita;

    @Column(name = "hora_cita")
    private LocalTime horaCita;

    @Column(name = "id_estado")
    private Integer idEstado;

    private String observaciones;

    @Column(name = "costo_consulta")
    private Double costoConsulta;

    @Column(name = "descuento_aplicado")
    private Double descuentoAplicado;

    @Column(name = "costo_total")
    private Double costoTotal;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "id_usuario_registro")
    private Integer idUsuarioRegistro;

    @Column(name = "hora_llegada")
    private LocalDateTime horaLlegada;
}