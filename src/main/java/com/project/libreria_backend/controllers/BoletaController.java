package com.project.libreria_backend.controllers;

import com.project.libreria_backend.services.interfaces.IDocxMerge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BoletaController {
    @Autowired
    private IDocxMerge services;
    @GetMapping("/boleta/descargar/{id_venta}")
    public ResponseEntity<InputStreamResource> descargarBoleta(@PathVariable int id_venta) {
        try {
            File file = services.descargarBoleta(id_venta);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(file.length())
                    .body(resource);
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Boleta no encontrada", e
            );
        }
    }

    @GetMapping("/boleta/descargar/ultima")
    public ResponseEntity<InputStreamResource> descargarUltimaBoleta() {
        try {
            File file = services.descargarUltimaBoleta();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(file.length())
                    .body(resource);
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Última boleta no encontrada", e
            );
        }
    }
}
