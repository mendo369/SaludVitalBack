package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.EstadoCita;
import com.example.SaludVital.domain.repositories.EstadoCitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoCitaService {

    @Autowired
    private EstadoCitaRepository estadoCitaRepository;

    // Crear un nuevo estado de cita
    public EstadoCita createEstadoCita(EstadoCita estadoCita) {
        return estadoCitaRepository.save(estadoCita);
    }

    // Obtener todos los estados de cita
    public List<EstadoCita> getAllEstadosCita() {
        return estadoCitaRepository.findAll();
    }

    // Obtener un estado de cita por ID
    public EstadoCita getEstadoCitaById(Integer id) {
        return estadoCitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de cita no encontrado con ID: " + id));
    }

    // Actualizar un estado de cita
    public EstadoCita updateEstadoCita(Integer id, EstadoCita estadoCitaDetails) {
        EstadoCita estadoCita = getEstadoCitaById(id);

        estadoCita.setNombreEstado(estadoCitaDetails.getNombreEstado());
        estadoCita.setDescripcion(estadoCitaDetails.getDescripcion());

        return estadoCitaRepository.save(estadoCita);
    }

    // Eliminar un estado de cita
    public void deleteEstadoCita(Integer id) {
        if (!estadoCitaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Estado de cita no encontrado");
        }
        estadoCitaRepository.deleteById(id);
    }
}