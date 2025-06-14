package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.Aseguradora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AseguradoraRepository extends JpaRepository<Aseguradora, Integer> {
}