package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.ServicioAdicional;
import com.example.SaludVital.domain.repositories.ServicioAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    public Map<String, Object> getMonthlyAdditionalServicesStats() {
        try {
            Object[] stats = servicioAdicionalRepository.getMonthlyAdditionalServicesStats();

            if (stats == null || stats.length < 2) {
                return Map.of(
                        "cantidad_servicios", 0,
                        "total_ingresos", 0.0
                );
            }

            return Map.of(
                    "cantidad_servicios", (stats[0] != null ? stats[0] : 0),
                    "total_ingresos", (stats[1] != null ? stats[1] : 0.0)
            );
        } catch (Exception e) {
            System.err.println("Error al obtener estadísticas mensuales: " + e.getMessage());
            e.printStackTrace();
            return Map.of(
                    "cantidad_servicios", 0,
                    "total_ingresos", 0.0
            );
        }
    }

    public Map<String, Object> getMostRequestedService() {
        Object[] result = servicioAdicionalRepository.getMostRequestedServiceStats();

        if (result == null || result.length < 2) {
            return Map.of("nombre_servicio", "Ninguno", "total_cantidad", 0);
        }

        return Map.of(
                "nombre_servicio", result[0],
                "total_cantidad", result[1]
        );
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