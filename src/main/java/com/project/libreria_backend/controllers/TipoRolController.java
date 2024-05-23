package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dao.TipoRol;
import com.project.libreria_backend.services.interfaces.ITipoRol;
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
public class TipoRolController {
    @Autowired
    private ITipoRol services;

    @GetMapping("/tipoRoles")
    public ResponseEntity<List<TipoRol>> getAllRoles(){
        List<TipoRol> lstRoles = services.getAllTipoRol();
        if(!lstRoles.isEmpty()){
            return ResponseEntity.ok(lstRoles);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
