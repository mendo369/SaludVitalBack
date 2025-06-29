package com.example.SaludVital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Nueva forma de deshabilitar CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/especialidades/**").permitAll()
                        .requestMatchers("/api/medicos/**").permitAll()
                        .requestMatchers("/api/aseguradoras/**").permitAll()
                        .requestMatchers("/api/pacientes/**").permitAll()
                        .requestMatchers("/api/tipos-paciente/**").permitAll()
                        .requestMatchers("/api/tipos-consulta/**").permitAll()
                        .requestMatchers("/api/estados-cita/**").permitAll()
                        .requestMatchers("/api/citas/**").permitAll()
                        .requestMatchers("/api/facturas/**").permitAll()
                        .requestMatchers("/api/detalles-factura/**").permitAll()
                        .requestMatchers("/api/servicios-adicionales/**").permitAll()
                        .requestMatchers("/api/servicios-paciente/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}