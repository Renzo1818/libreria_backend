package com.project.libreria_backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO {
    private int id_usuario;
    private int id_rol;
    private String mail;
    private String contrasena;
    private boolean estado;
    public UsuarioDTO() {
    }
}
