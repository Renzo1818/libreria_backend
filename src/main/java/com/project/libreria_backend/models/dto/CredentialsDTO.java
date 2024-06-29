package com.project.libreria_backend.models.dto;

import lombok.Data;

@Data
public class CredentialsDTO {
    private String mail;
    private String contrasena;

    public CredentialsDTO() {
    }
}
