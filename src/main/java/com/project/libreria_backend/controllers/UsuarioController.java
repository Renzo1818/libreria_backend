package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dto.UsuarioDTO;
import com.project.libreria_backend.services.interfaces.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {
    @Autowired
    private IUsuario services;

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        return ResponseEntity.ok(services.getAllUsuarios());
    }
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable int id) {
        UsuarioDTO usuario = services.getUsuario(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Void> agregar(@RequestBody UsuarioDTO usuarioDTO){
        services.guardarUsuario(usuarioDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Void> modificar(@RequestBody UsuarioDTO usuarioDTO, @PathVariable int id){
        services.modificarUsuario(usuarioDTO, id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }
}
