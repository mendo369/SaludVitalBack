package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByPaciente_IdPaciente(Integer idPaciente);
}