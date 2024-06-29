package com.project.libreria_backend.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class LibroDTO {
    private int id_libro;
    private int id_autor;
    private int id_editorial;
    private int id_categoria;
    private String titulo;
    private String descripcion;
    private Double precio_unitario;
    private int stock;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_publicacion;
    private String ruta_img;
    private boolean estado;

    public LibroDTO(){
    }
}
