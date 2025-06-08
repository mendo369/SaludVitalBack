package com.example.SaludVital.models.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private Integer idUsuario;
    private String username;
    private String nombre;
    private String apellido;
    private Integer idRol;

    public AuthResponse(String token, Integer idUsuario, String username, String nombre, String apellido, Integer idRol) {
        this.token = token;
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idRol = idRol;
    }
}
