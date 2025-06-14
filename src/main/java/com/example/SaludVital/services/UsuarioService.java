package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.RolUsuario;
import com.example.SaludVital.domain.entities.Usuario;
import com.example.SaludVital.domain.repositories.RolUsuarioRepository;
import com.example.SaludVital.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        // Validar que el username sea único
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }

        // Validar que el rol exista
        if (!rolUsuarioRepository.existsById(usuario.getIdRol())) {
            throw new RuntimeException("El rol especificado no existe.");
        }

        // Asignar valores por defecto
        usuario.setActivo(true);
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setUltimoAcceso(null); // Se actualizará después del primer login

        return usuarioRepository.save(usuario);
    }
}