package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.ServicioPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioPacienteRepository extends JpaRepository<ServicioPaciente, Integer> {
    List<ServicioPaciente> findByCita_IdCita(Integer idCita);
}