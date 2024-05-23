package com.project.libreria_backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinEmpleadoUsuarioDTO {
    private int id_empleado;
    private String mail;
    private String nombre;
    private String apellido;
    private String rol;
    private boolean estado;
    public JoinEmpleadoUsuarioDTO(String nombre, String apellido, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
    }
}
