package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.DetalleVentaConsolidadoDTO;

import java.io.File;
import java.util.List;

public interface IDocxMerge {
    void generarBoleta(String outputFolderPath, String outputFileName, List<DetalleVentaConsolidadoDTO> detallesVentaConsolidadoDTOS, int id_venta) throws Exception;
    File descargarBoleta(int id_venta);
    File descargarUltimaBoleta();
}
