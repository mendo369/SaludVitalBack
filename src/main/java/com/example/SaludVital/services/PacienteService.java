package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.Paciente;
import com.example.SaludVital.domain.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente createPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente getPacienteById(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    public Paciente updatePaciente(Integer id, Paciente pacienteDetails) {
        Paciente paciente = getPacienteById(id);

        paciente.setNumeroIdentificacion(pacienteDetails.getNumeroIdentificacion());
        paciente.setTipoIdentificacion(pacienteDetails.getTipoIdentificacion());
        paciente.setNombre(pacienteDetails.getNombre());
        paciente.setApellido(pacienteDetails.getApellido());
        paciente.setFechaNacimiento(pacienteDetails.getFechaNacimiento());
        paciente.setTelefono(pacienteDetails.getTelefono());
        paciente.setEmail(pacienteDetails.getEmail());
        paciente.setDireccion(pacienteDetails.getDireccion());
        paciente.setTipoPaciente(pacienteDetails.getTipoPaciente());
        paciente.setAseguradora(pacienteDetails.getAseguradora());
        paciente.setContactoEmergencia(pacienteDetails.getContactoEmergencia());
        paciente.setTelefonoEmergencia(pacienteDetails.getTelefonoEmergencia());
        paciente.setActivo(pacienteDetails.getActivo());
        paciente.setFechaRegistro(pacienteDetails.getFechaRegistro());
        paciente.setNumeroPoliza(pacienteDetails.getNumeroPoliza());

        return pacienteRepository.save(paciente);
    }

    public void deletePaciente(Integer id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }
}