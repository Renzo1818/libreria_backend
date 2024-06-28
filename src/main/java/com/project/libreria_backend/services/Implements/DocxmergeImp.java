package com.project.libreria_backend.services.Implements;

import com.docxmerge.Docxmerge;
import com.project.libreria_backend.models.dao.Venta;
import com.project.libreria_backend.models.dto.DetalleVentaConsolidadoDTO;
import com.project.libreria_backend.repository.VentaRepository;
import com.project.libreria_backend.services.interfaces.IDocxMerge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DocxmergeImp implements IDocxMerge {
    @Autowired
    private VentaRepository repositoryVenta;
    private static final String API_KEY = "J8H0v4LpPGnYIWnri0si6wemxGEia4"; // Cambia esto a tu API key
    private static final String API_URL = "https://api.docxmerge.com";
    private static final String TEMPLATE_NAME = "templateboleta";

    @Override
    public void generarBoleta(String outputFolderPath, String outputFileName, List<DetalleVentaConsolidadoDTO> detallesVentaConsolidadoDTOS, int id_venta) throws Exception {
        Docxmerge docxmerge = new Docxmerge(API_KEY, "default-qbgqkbf", API_URL);

        HashMap<String, Object> templateData = new HashMap<>();
        templateData.put("zip", "Perú");
        templateData.put("city", "Lima");
        templateData.put("date", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        templateData.put("invid", "BOLETA");
        templateData.put("notes", "Orden de Venta: " + id_venta);
        templateData.put("state", "Lima");
        templateData.put("address", "La Molina");
        templateData.put("company", "USIL LIBRERIA");

        List<HashMap<String, Object>> orders = new ArrayList<>();
        double totalAcumulado = 0.0;
        for (DetalleVentaConsolidadoDTO detalle : detallesVentaConsolidadoDTOS) {
            totalAcumulado += detalle.getImporte() * detalle.getCantidad();
            HashMap<String, Object> order = new HashMap<>();
            order.put("description", detalle.getTitulo());
            order.put("qty", detalle.getCantidad());
            order.put("price", detalle.getImporte());
            order.put("total", Math.round(detalle.getImporte() * detalle.getCantidad() * 10.0) / 10.0);
            orders.add(order);
        }
        templateData.put("subtotal", Math.round(totalAcumulado * 10.0) /10.0);
        templateData.put("total", Math.round(totalAcumulado * 10.0) /10.0);

        templateData.put("orders", orders);

        byte[] bytes = docxmerge.renderTemplate(TEMPLATE_NAME, templateData, "PDF", "latest");

        File outputFile = new File(outputFolderPath, outputFileName);
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(bytes);
        }
    }

    @Override
    public File descargarBoleta(int id_venta) {
        String rutaBoleta = repositoryVenta.findById(id_venta).get().getRuta_boleta();
        File file = new File(rutaBoleta);
        if (!file.exists()) {
            try {
                throw new FileNotFoundException("Boleta no encontrada en la ruta especificada.");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    @Override
    public File descargarUltimaBoleta() {
        Venta ultimaVenta = repositoryVenta.correlativoVenta();
        String rutaBoleta = repositoryVenta.findById(ultimaVenta.getId_venta()).get().getRuta_boleta();
        File file = new File(rutaBoleta);
        if (!file.exists()) {
            try {
                throw new FileNotFoundException("Boleta no encontrada en la ruta especificada.");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }
}
