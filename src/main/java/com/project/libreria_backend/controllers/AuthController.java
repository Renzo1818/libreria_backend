package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dto.AuthResponse;
import com.project.libreria_backend.models.dto.CredentialsDTO;
import com.project.libreria_backend.services.interfaces.IAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IAuth services;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody CredentialsDTO credentialsDTO){
        return ResponseEntity.ok(services.login(credentialsDTO));
    }
}
