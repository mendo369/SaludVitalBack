package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByPaciente_IdPaciente(Integer idPaciente);

    // Contar todas las citas canceladas
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.estadoCita.idEstado = 4")
    Long countAllCanceledAppointments();

    // Opcional: si también quieres obtener la lista de citas canceladas
    @Query("SELECT c FROM Cita c WHERE c.estadoCita.idEstado = 4")
    List<Cita> findAllCanceledAppointments();

    @Query(value = "SELECT " +
            "m.id_medico AS idMedico, " +
            "m.nombre AS nombreMedico, " +
            "COUNT(c.id_cita) AS cantidadCitas " +
            "FROM medicos m " +
            "LEFT JOIN citas c ON m.id_medico = c.id_medico " +
            "GROUP BY m.id_medico, m.nombre " +
            "ORDER BY cantidadCitas DESC", nativeQuery = true)
    List<Map<String, Object>> countCitasByMedico();

    List<Cita> findByMedico_IdMedico(Integer idMedico);
}