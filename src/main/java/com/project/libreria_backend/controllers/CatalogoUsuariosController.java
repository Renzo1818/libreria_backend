package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dto.JoinEmpleadoUsuarioDTO;
import com.project.libreria_backend.services.interfaces.ICatalogoUsuario;
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
public class CatalogoUsuariosController {
    @Autowired
    private ICatalogoUsuario services;

    @GetMapping("/catalogoUsuarios")
    public ResponseEntity<List<JoinEmpleadoUsuarioDTO>> getCatalogoUsuarios(){
        List<JoinEmpleadoUsuarioDTO> catalogoUsuariosDTO = services.getCatalogoUsuario();
        if(!catalogoUsuariosDTO.isEmpty()){
            return ResponseEntity.ok(catalogoUsuariosDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
