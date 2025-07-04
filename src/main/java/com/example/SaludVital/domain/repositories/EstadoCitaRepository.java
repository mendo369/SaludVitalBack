package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Integer> {
    Optional<EstadoCita> findByNombreEstado(String nombreEstado);
}