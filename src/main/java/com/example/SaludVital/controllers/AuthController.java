package com.example.SaludVital.controllers;

import com.example.SaludVital.models.request.LoginRequest;
import com.example.SaludVital.models.response.AuthResponse;
import com.example.SaludVital.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        System.out.println(request);
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}