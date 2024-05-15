package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dto.EjemplarLibroDTO;
import com.project.libreria_backend.services.interfaces.IEjemplarLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EjemplarLibroController {
    @Autowired
    private IEjemplarLibro services;

    @GetMapping("/ejemplares")
    public ResponseEntity<List<EjemplarLibroDTO>> getAll(){
        return ResponseEntity.ok(services.getAllEjemplares());
    }
    @GetMapping("/ejemplares/{id}")
    public ResponseEntity<EjemplarLibroDTO> getEjemplar(@PathVariable int id){
        EjemplarLibroDTO ejemplarLibroDTO = services.getEjemplar(id);
        if(ejemplarLibroDTO != null){
            return ResponseEntity.ok(ejemplarLibroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/ejemplares")
    public ResponseEntity<?> agregar(@RequestBody EjemplarLibroDTO ejemplarLibroDTO){
        services.guardarEjemplar(ejemplarLibroDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/ejemplares/{id}")
    public ResponseEntity<Void> modificar(@RequestBody EjemplarLibroDTO ejemplarLibroDTO, @PathVariable int id){
        services.modificarEjemplar(ejemplarLibroDTO, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ejemplares/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarEjemplar(id);
        return ResponseEntity.ok().build();
    }

}
