package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dao.Carrito;
import com.project.libreria_backend.services.interfaces.ICarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CarritoController {
    @Autowired
    private ICarrito services;
    @GetMapping("/carrito/items")
    public ResponseEntity<List<Carrito>> getAll(){
        return ResponseEntity.ok(services.getAllItems());
    }

    @PostMapping("/carrito/agregar")
    public ResponseEntity<?> guardar(@RequestBody Carrito carrito){
        services.agregarItemCarrito(carrito);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/carrito/{id}")
    public ResponseEntity<Void> modificar(@RequestBody Carrito carrito, @PathVariable int id){
        services.modificarCantidadItemCarrito(carrito, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/carrito/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarItemCarrito(id);
        return ResponseEntity.ok().build();
    }
}
