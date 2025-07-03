package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.ServicioPaciente;
import com.example.SaludVital.domain.repositories.ServicioPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioPacienteService {

    @Autowired
    private ServicioPacienteRepository servicioPacienteRepository;

    // Crear un nuevo registro de servicio paciente
    public ServicioPaciente createServicioPaciente(ServicioPaciente servicioPaciente) {
        return servicioPacienteRepository.save(servicioPaciente);
    }

    // Obtener todos los servicios pacientes
    public List<ServicioPaciente> getAllServiciosPaciente() {
        return servicioPacienteRepository.findAll();
    }

    // Obtener un servicio paciente por ID
    public ServicioPaciente getServicioPacienteById(Integer id) {
        return servicioPacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio paciente no encontrado con ID: " + id));
    }

    // Actualizar un servicio paciente
    public ServicioPaciente updateServicioPaciente(Integer id, ServicioPaciente servicioPacienteDetails) {
        ServicioPaciente servicioPaciente = getServicioPacienteById(id);

        servicioPaciente.setServicioAdicional(servicioPacienteDetails.getServicioAdicional());
        servicioPaciente.setCita(servicioPacienteDetails.getCita());
        servicioPaciente.setCantidad(servicioPacienteDetails.getCantidad());

        return servicioPacienteRepository.save(servicioPaciente);
    }

    // Eliminar un servicio paciente
    public void deleteServicioPaciente(Integer id) {
        if (!servicioPacienteRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Servicio paciente no encontrado");
        }
        servicioPacienteRepository.deleteById(id);
    }
}