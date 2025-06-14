package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.Especialidad;
import com.example.SaludVital.domain.repositories.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public Especialidad createEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public List<Especialidad> getAllEspecialidades() {
        return especialidadRepository.findAll();
    }

    public Especialidad getEspecialidadById(Integer id) {
        return especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + id));
    }

    public Especialidad updateEspecialidad(Integer id, Especialidad especialidadDetails) {
        Especialidad especialidad = getEspecialidadById(id);
        especialidad.setNombreEspecialidad(especialidadDetails.getNombreEspecialidad());
        especialidad.setDescripcion(especialidadDetails.getDescripcion());
        return especialidadRepository.save(especialidad);
    }

    public void deleteEspecialidad(Integer id) {
        if (!especialidadRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Especialidad no encontrada");
        }
        especialidadRepository.deleteById(id);
    }
}