package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    List<Medico> findByIdEspecialidad(Integer idEspecialidad);
}