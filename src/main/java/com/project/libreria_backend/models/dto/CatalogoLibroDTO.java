package com.project.libreria_backend.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
@AllArgsConstructor
public class CatalogoLibroDTO {
    private int id_libro;
    private String titulo;
    private String autor;
    private String categoria;
    private String editorial;
    private int stock;
    private double precio_unitario;
    private boolean estado;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_publicacion;
    private String descripcion;
    private String ruta_img;
}
