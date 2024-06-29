package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dto.DetalleVentaDTO;
import com.project.libreria_backend.services.interfaces.IDetalleVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DetelleVentaController {
    @Autowired
    private IDetalleVenta services;
    @GetMapping("/detalle")
    public ResponseEntity<List<DetalleVentaDTO>> getAll(){
        return ResponseEntity.ok(services.getAllDetalleVenta());
    }

    @GetMapping("/detalle/{id_libro}")
    public ResponseEntity<List<DetalleVentaDTO>> getDetalleVenta(@PathVariable int id_libro){
        return ResponseEntity.ok(services.getDetalleVenta(id_libro));
    }
    @PostMapping("/detalle")
    public ResponseEntity<?> agregar(){
        services.agregarDetalleVenta();
        return ResponseEntity.ok().build();
    }
}
