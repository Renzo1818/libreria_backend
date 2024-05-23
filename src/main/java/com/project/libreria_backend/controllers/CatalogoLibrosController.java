package com.project.libreria_backend.controllers;


import com.project.libreria_backend.models.dto.CatalogoLibroDTO;
import com.project.libreria_backend.services.interfaces.ICatalogoLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CatalogoLibrosController {
    @Autowired
    private ICatalogoLibro services;

    @GetMapping("/catalogoLibros")
    public ResponseEntity<List<CatalogoLibroDTO>> getCatalogoLibros(){
        List<CatalogoLibroDTO> catalogoLibrosDTO = services.getCatalogoLibros();
        if(!catalogoLibrosDTO.isEmpty()){
            return ResponseEntity.ok(catalogoLibrosDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/catalogoLibros/titulo/{titulo}")
    public ResponseEntity<CatalogoLibroDTO> getLibroByTitulo(@PathVariable String titulo){
        CatalogoLibroDTO catalogoLibroDTO = services.getLibroByTitulo(titulo);
        if(catalogoLibroDTO != null){
            return ResponseEntity.ok(catalogoLibroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/catalogoLibros/autor/{autor}")
    public ResponseEntity<List<CatalogoLibroDTO>> getLibroByNombreApellidoAutor(@PathVariable String autor){
        List<CatalogoLibroDTO> catalogoLibroDTO = services.getLibroByNombreApellidoAutor(autor);
        if(!catalogoLibroDTO.isEmpty()){
            return ResponseEntity.ok(catalogoLibroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/catalogoLibros/categoria/{categoria}")
    public ResponseEntity<List<CatalogoLibroDTO>> getLibroByCategoria(@PathVariable String categoria){
        List<CatalogoLibroDTO> catalogoLibroDTO = services.getLibroByCategoria(categoria);
        if(!catalogoLibroDTO.isEmpty()){
            return ResponseEntity.ok(catalogoLibroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/catalogoLibros/editorial/{editorial}")
    public ResponseEntity<List<CatalogoLibroDTO>> getLibroByEditorial(@PathVariable String editorial){
        List<CatalogoLibroDTO> catalogoLibroDTO = services.getLibroByEditorial(editorial);
        if(!catalogoLibroDTO.isEmpty()){
            return ResponseEntity.ok(catalogoLibroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/catalogoLibros/detalle/{idLibro}")
    public ResponseEntity<CatalogoLibroDTO> getLibroByCatalogo(@PathVariable int idLibro){
        CatalogoLibroDTO catalogoLibroDTO = services.getLibroByCatalogo(idLibro);
        if(catalogoLibroDTO != null){
            return ResponseEntity.ok(catalogoLibroDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
