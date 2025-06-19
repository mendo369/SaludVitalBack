package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.TipoPaciente;
import com.example.SaludVital.domain.repositories.TipoPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPacienteService {

    @Autowired
    private TipoPacienteRepository tipoPacienteRepository;

    // Crear un nuevo tipo de paciente
    public TipoPaciente createTipoPaciente(TipoPaciente tipoPaciente) {
        return tipoPacienteRepository.save(tipoPaciente);
    }

    // Obtener todos los tipos de pacientes
    public List<TipoPaciente> getAllTiposPacientes() {
        return tipoPacienteRepository.findAll();
    }

    // Obtener un tipo de paciente por ID
    public TipoPaciente getTipoPacienteById(Integer id) {
        return tipoPacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de paciente no encontrado con ID: " + id));
    }

    // Actualizar un tipo de paciente
    public TipoPaciente updateTipoPaciente(Integer id, TipoPaciente tipoPacienteDetails) {
        TipoPaciente tipoPaciente = getTipoPacienteById(id);

        tipoPaciente.setNombreTipo(tipoPacienteDetails.getNombreTipo());
        tipoPaciente.setPorcentajeDescuento(tipoPacienteDetails.getPorcentajeDescuento());
        tipoPaciente.setDescripcion(tipoPacienteDetails.getDescripcion());

        return tipoPacienteRepository.save(tipoPaciente);
    }

    // Eliminar un tipo de paciente
    public void deleteTipoPaciente(Integer id) {
        if (!tipoPacienteRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Tipo de paciente no encontrado");
        }
        tipoPacienteRepository.deleteById(id);
    }
}