package com.project.libreria_backend.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Carrito {
    private int id_libro;
    private String titulo;
    private int cantidad;
    private double importe;
}
