package com.example.SaludVital.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedico;

    @Column(name = "numero_licencia", nullable = false, unique = true)
    private String numeroLicencia;

    private String nombre;
    private String apellido;

    @Column(name = "id_especialidad")
    private Integer idEspecialidad;

    private String telefono;
    private String email;

    @Column(name = "horario_inicio")
    private LocalTime horarioInicio;

    @Column(name = "horario_fin")
    private LocalTime horarioFin;

    private Boolean activo;

    @Column(name = "id_usuario")
    private Integer idUsuario;
}