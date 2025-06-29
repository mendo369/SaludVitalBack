package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.Cita;
import com.example.SaludVital.domain.repositories.CitaRepository;
import com.example.SaludVital.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ CREAR una nueva cita
    public Cita createCita(Cita cita) {
        // Validar que el usuario exista
        if (cita.getUsuarioRegistro() == null ||
                !usuarioRepository.existsById(cita.getUsuarioRegistro().getIdUsuario())) {
            throw new RuntimeException("Usuario no válido o no encontrado");
        }

        // Establecer fecha de creación si no está definida
        if (cita.getFechaCreacion() == null) {
            cita.setFechaCreacion(LocalDateTime.now());
        }

        return citaRepository.save(cita);
    }

    // ✅ OBTENER todas las citas
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    // ✅ OBTENER una cita por ID
    public Cita getCitaById(Integer id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }

    // ✅ ACTUALIZAR una cita
    public Cita updateCita(Integer id, Cita citaDetails) {
        Cita cita = getCitaById(id);

        // Actualizar campos permitidos
        if (citaDetails.getPaciente() != null) {
            cita.setPaciente(citaDetails.getPaciente());
        }
        if (citaDetails.getMedico() != null) {
            cita.setMedico(citaDetails.getMedico());
        }
        if (citaDetails.getTipoConsulta() != null) {
            cita.setTipoConsulta(citaDetails.getTipoConsulta());
        }
        if (citaDetails.getEstadoCita() != null) {
            cita.setEstadoCita(citaDetails.getEstadoCita());
        }
        if (citaDetails.getFechaCita() != null) {
            cita.setFechaCita(citaDetails.getFechaCita());
        }
        if (citaDetails.getHoraCita() != null) {
            cita.setHoraCita(citaDetails.getHoraCita());
        }
        if (citaDetails.getObservaciones() != null) {
            cita.setObservaciones(citaDetails.getObservaciones());
        }
        if (citaDetails.getCostoConsulta() != null) {
            cita.setCostoConsulta(citaDetails.getCostoConsulta());
        }
        if (citaDetails.getDescuentoAplicado() != null) {
            cita.setDescuentoAplicado(citaDetails.getDescuentoAplicado());
        }
        if (citaDetails.getCostoTotal() != null) {
            cita.setCostoTotal(citaDetails.getCostoTotal());
        }
        if (citaDetails.getUsuarioRegistro() != null &&
                usuarioRepository.existsById(citaDetails.getUsuarioRegistro().getIdUsuario())) {
            cita.setUsuarioRegistro(citaDetails.getUsuarioRegistro());
        }
        if (citaDetails.getHoraLlegada() != null) {
            cita.setHoraLlegada(citaDetails.getHoraLlegada());
        }

        return citaRepository.save(cita);
    }

    // ✅ ELIMINAR una cita
    public void deleteCita(Integer id) {
        if (!citaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Cita no encontrada");
        }
        citaRepository.deleteById(id);
    }
}