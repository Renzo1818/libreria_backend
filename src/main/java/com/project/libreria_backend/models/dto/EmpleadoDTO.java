package com.project.libreria_backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpleadoDTO {
    private int id_empleado;
    private String nombre;
    private String apellido;
    private String dni;
    private boolean estado;

    public EmpleadoDTO() {
    }
}
