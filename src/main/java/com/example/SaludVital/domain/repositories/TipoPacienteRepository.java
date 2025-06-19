package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.TipoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPacienteRepository extends JpaRepository<TipoPaciente, Integer> {
}