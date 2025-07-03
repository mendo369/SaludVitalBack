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
            Object raw = servicioAdicionalRepository.getMonthlyAdditionalServicesStats();

            if (raw == null) {
                System.out.println("Resultado nulo");
                return Map.of(
                        "cantidad_servicios", 0L,
                        "total_ingresos", 0.0
                );
            }

            Object[] stats = (Object[]) raw;

            System.out.println("Raw stats: " + Arrays.toString(stats));

            Long cantidadServicios = (stats[0] != null) ? ((Number) stats[0]).longValue() : 0L;
            Double totalIngresos = (stats[1] != null) ? ((Number) stats[1]).doubleValue() : 0.0;

            System.out.println("Cantidad de servicios: " + cantidadServicios);
            System.out.println("Total ingresos: " + totalIngresos);

            return Map.of(
                    "cantidad_servicios", cantidadServicios,
                    "total_ingresos", totalIngresos
            );

        } catch (Exception e) {
            System.err.println("Error al obtener estadísticas mensuales: " + e.getMessage());
            e.printStackTrace();
            return Map.of(
                    "cantidad_servicios", 0L,
                    "total_ingresos", 0.0
            );
        }
    }


    public Map<String, Object> getMostRequestedService() {
        try {
            Object raw = servicioAdicionalRepository.getMostRequestedServiceStats();

            if (raw == null) {
                return Map.of("nombre_servicio", "Ninguno", "total_cantidad", 0);
            }

            Object[] result = (Object[]) raw;

            System.out.println("Resultado procesado: " + Arrays.toString(result));

            if (result.length < 2 || result[0] == null || result[1] == null) {
                return Map.of("nombre_servicio", "Ninguno", "total_cantidad", 0);
            }

            return Map.of(
                    "nombre_servicio", result[0].toString(),
                    "total_cantidad", ((Number) result[1]).intValue()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("nombre_servicio", "Ninguno", "total_cantidad", 0);
        }
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