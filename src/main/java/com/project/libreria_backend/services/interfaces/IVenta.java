package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.VentaDTO;

import java.util.List;

public interface IVenta {
    List<VentaDTO> getAllVentas();
    VentaDTO getVenta(int id);
    void agregarVenta(VentaDTO ventaDTO);
    void eliminarVenta(int id);
}
