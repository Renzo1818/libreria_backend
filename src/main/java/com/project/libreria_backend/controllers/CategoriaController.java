package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dao.Categoria;
import com.project.libreria_backend.services.interfaces.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoriaController {
    @Autowired
    private ICategoria services;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getAll(){
        return ResponseEntity.ok(services.getAllCategorias());
    }
}
