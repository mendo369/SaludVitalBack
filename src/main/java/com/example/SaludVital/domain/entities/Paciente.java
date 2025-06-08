package com.example.SaludVital.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @Column(name = "numero_identificacion", nullable = false, unique = true)
    private String numeroIdentificacion;

    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion = "CC";

    private String nombre;
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String telefono;
    private String email;
    private String direccion;

    @Column(name = "id_tipo_paciente")
    private Integer idTipoPaciente;

    @Column(name = "id_aseguradora")
    private Integer idAseguradora;

    @Column(name = "numero_poliza")
    private String numeroPoliza;

    @Column(name = "contacto_emergencia")
    private String contactoEmergencia;

    @Column(name = "telefono_emergencia")
    private String telefonoEmergencia;

    private Boolean activo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}