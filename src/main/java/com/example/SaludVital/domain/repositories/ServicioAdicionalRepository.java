package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.ServicioAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioAdicionalRepository extends JpaRepository<ServicioAdicional, Integer> {
    @Query(value = "SELECT COUNT(sp.id_servicio_paciente), SUM(sa.costo * sp.cantidad) "
            + "FROM servicios_paciente sp "
            + "JOIN servicios_adicionales sa ON sp.id_servicio = sa.id_servicio "
            + "WHERE DATE_TRUNC('month', sp.fecha_servicio) = DATE_TRUNC('month', CURRENT_DATE)",
            nativeQuery = true)
    Object getMonthlyAdditionalServicesStats();

    @Query(value = "SELECT sa.nombre_servicio, SUM(sp.cantidad) AS total_cantidad "
            + "FROM servicios_paciente sp "
            + "JOIN servicios_adicionales sa ON sp.id_servicio = sa.id_servicio "
            + "WHERE DATE_TRUNC('month', sp.fecha_servicio) = DATE_TRUNC('month', CURRENT_DATE) "
            + "GROUP BY sa.id_servicio, sa.nombre_servicio "
            + "ORDER BY total_cantidad DESC LIMIT 1",
            nativeQuery = true)
    Object getMostRequestedServiceStats(); // ← no es Object[]


}