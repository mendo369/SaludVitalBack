package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.Usuario;
import com.example.SaludVital.domain.repositories.UsuarioRepository;
import com.example.SaludVital.models.request.LoginRequest;
import com.example.SaludVital.models.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AuthResponse login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(request.getUsername());

        if (usuarioOpt.isEmpty()) {
            throw new BadCredentialsException("Documento o contraseña incorrectos");
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getActivo()) {
            throw new BadCredentialsException("Usuario inactivo");
        }

        // Comparación directa, sin encriptación
        if (!request.getPassword().equals(usuario.getPasswordHash())) {
            throw new BadCredentialsException("Documento o contraseña incorrectos");
        }

        return new AuthResponse(
                "dummy-token",
                usuario.getIdUsuario(),
                usuario.getUsername(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getIdRol()
        );
    }
}