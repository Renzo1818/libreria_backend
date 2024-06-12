package com.project.libreria_backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleVentaConsolidadoDTO {
    private int id_venta;
    private int id_libro;
    private String titulo;
    private int cantidad;
    private double importe;

    public DetalleVentaConsolidadoDTO(int id_venta, int id_libro, String titulo, int cantidad, double importe) {
        this.id_venta = id_venta;
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.importe = importe;
    }
    @Override
    public String toString() {
        return "DetalleVentaConsolidadoDTO{" +
                "id_venta=" + id_venta +
                ", id_libro=" + id_libro +
                ", titulo='" + titulo + '\'' +
                ", cantidad=" + cantidad +
                ", importe=" + importe +
                '}';
    }
}
