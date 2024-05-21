package com.project.libreria_backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private int id_usuario;
    private String nombre;
    private String apellido;
    private String rol;
    private String token;
}
