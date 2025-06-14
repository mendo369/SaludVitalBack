package com.example.SaludVital.domain.repositories;

import com.example.SaludVital.domain.entities.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Integer> {
    Optional<RolUsuario> findByNombreRol(String nombreRol);
}