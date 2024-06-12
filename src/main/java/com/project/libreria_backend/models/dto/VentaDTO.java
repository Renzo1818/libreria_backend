package com.project.libreria_backend.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class VentaDTO {
    private int id_venta;
    private int id_empleado;
    private double importe_total;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha_emision;
    private boolean estado;

    public VentaDTO(){

    }
}
