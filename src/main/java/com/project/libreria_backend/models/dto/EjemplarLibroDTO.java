package com.project.libreria_backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EjemplarLibroDTO {
    private int id_ejemplar;
    private int id_libro;
    private int num_ingreso;
    private boolean estado_ejemplar;

    public EjemplarLibroDTO(){
    }
}
