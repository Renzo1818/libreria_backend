package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dto.VentaDTO;
import com.project.libreria_backend.services.interfaces.IVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class VentaController {
    @Autowired
    private IVenta services;
    @GetMapping("/ventas")
    public ResponseEntity<List<VentaDTO>> getAll(){
        return ResponseEntity.ok(services.getAllVentas());
    }
    @GetMapping("/ventas/{id}")
    public ResponseEntity<VentaDTO> getReserva(@PathVariable int id){
        VentaDTO ventaDTO = services.getVenta(id);
        if(ventaDTO != null){
            return ResponseEntity.ok(ventaDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ventas")
    public ResponseEntity<?> agregar(@RequestBody VentaDTO ventaDTO){
        services.agregarVenta(ventaDTO);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/ventas/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarVenta(id);
        return ResponseEntity.ok().build();
    }

}
