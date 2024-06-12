package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.DetalleVentaDTO;

import java.util.List;

public interface IDetalleVenta {
    List<DetalleVentaDTO> getAllDetalleVenta();
    List<DetalleVentaDTO> getDetalleVenta(int id_venta);
    void agregarDetalleVenta();

}
