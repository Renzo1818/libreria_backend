package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dto.LibroDTO;
import com.project.libreria_backend.services.interfaces.ILIbro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LibroController {
    @Autowired
    private ILIbro services;

    @GetMapping("/libros")
    public ResponseEntity<List<LibroDTO>> getAll(){
        return ResponseEntity.ok(services.getAllLibros());
    }
    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroDTO> getLibro(@PathVariable int id){
        LibroDTO libroDTO = services.getLibro(id);
        if(libroDTO != null){
            return ResponseEntity.ok(libroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/libros")
    public ResponseEntity<?> agregar(@RequestBody LibroDTO libroDTO){
        services.agregarLibro(libroDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/libros/{id}")
    public ResponseEntity<Void> modificar(@RequestBody LibroDTO libroDTO, @PathVariable int id){
        services.modificarLibro(libroDTO, id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/libros/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarLibro(id);
        return ResponseEntity.ok().build();
    }
}
