package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.Medico;
import com.example.SaludVital.domain.entities.Usuario;
import com.example.SaludVital.domain.repositories.MedicoRepository;
import com.example.SaludVital.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear médico + usuario asociado
    public Medico saveMedico(Medico medico) {
        // Generar username: nombre.apellido.toLowerCase()
        String username = String.format("%s.%s", medico.getNombre(), medico.getApellido())
                .toLowerCase()
                .replace(" ", "");

        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El username '" + username + "' ya está en uso.");
        }

        // Generar email
        String email = username + "@saludvital.com.co";

        // Contraseña es el número de licencia
        String password = medico.getNumeroLicencia();

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPasswordHash(password);
        usuario.setNombre(medico.getNombre());
        usuario.setApellido(medico.getApellido());
        usuario.setEmail(email);
        usuario.setTelefono(medico.getTelefono());
        usuario.setIdRol(2);
        usuario.setActivo(medico.getActivo());
        usuario.setFechaCreacion(LocalDateTime.now());

        System.out.println("Usuario para guardar ------------> "+usuario);

        // Guardar usuario
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Asociar idUsuario al médico
        medico.setIdUsuario(usuarioGuardado.getIdUsuario());

        return medicoRepository.save(medico);
    }

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Medico getMedicoById(Integer id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));
    }

    public Medico updateMedico(Integer id, Medico medicoDetails) {
        Medico medico = getMedicoById(id);

        medico.setNumeroLicencia(medicoDetails.getNumeroLicencia());
        medico.setNombre(medicoDetails.getNombre());
        medico.setApellido(medicoDetails.getApellido());
        medico.setIdEspecialidad(medicoDetails.getIdEspecialidad());
        medico.setTelefono(medicoDetails.getTelefono());
        medico.setEmail(medicoDetails.getEmail());
        medico.setHorarioInicio(medicoDetails.getHorarioInicio());
        medico.setHorarioFin(medicoDetails.getHorarioFin());
        medico.setActivo(medicoDetails.getActivo());
        medico.setIdUsuario(medicoDetails.getIdUsuario());

        return medicoRepository.save(medico);
    }

    public void deleteMedico(Integer id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Médico no encontrado");
        }
        medicoRepository.deleteById(id);
    }
}