package com.project.libreria_backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DetalleVentaDTO {
    private int id_venta;
    private int id_libro;
    private int cantidad;
    private double importe;

    public DetalleVentaDTO(){

    }
}
