package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.ServicioAdicional;
import com.example.SaludVital.domain.repositories.ServicioAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioAdicionalService {

    @Autowired
    private ServicioAdicionalRepository servicioAdicionalRepository;

    // Crear un nuevo servicio adicional
    public ServicioAdicional createServicioAdicional(ServicioAdicional servicioAdicional) {
        return servicioAdicionalRepository.save(servicioAdicional);
    }

    // Obtener todos los servicios adicionales
    public List<ServicioAdicional> getAllServiciosAdicionales() {
        return servicioAdicionalRepository.findAll();
    }

    // Obtener un servicio adicional por ID
    public ServicioAdicional getServicioAdicionalById(Integer id) {
        return servicioAdicionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio adicional no encontrado con ID: " + id));
    }

    // Actualizar un servicio adicional
    public ServicioAdicional updateServicioAdicional(Integer id, ServicioAdicional servicioAdicionalDetails) {
        ServicioAdicional servicioAdicional = getServicioAdicionalById(id);

        servicioAdicional.setNombreServicio(servicioAdicionalDetails.getNombreServicio());
        servicioAdicional.setCosto(servicioAdicionalDetails.getCosto());
        servicioAdicional.setDescripcion(servicioAdicionalDetails.getDescripcion());
        servicioAdicional.setActivo(servicioAdicionalDetails.getActivo());

        return servicioAdicionalRepository.save(servicioAdicional);
    }

    // Eliminar un servicio adicional
    public void deleteServicioAdicional(Integer id) {
        if (!servicioAdicionalRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Servicio adicional no encontrado");
        }
        servicioAdicionalRepository.deleteById(id);
    }
}