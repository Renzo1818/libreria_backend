package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dao.Empleado;
import com.project.libreria_backend.models.dto.EmpleadoDTO;
import com.project.libreria_backend.services.interfaces.IEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class EmpleadoController {
    @Autowired
    private IEmpleado services;

    @GetMapping("/empleados")
    public ResponseEntity<List<EmpleadoDTO>> getAll(){
        return ResponseEntity.ok(services.getAllEmpleados());
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<EmpleadoDTO> getById(@PathVariable int id){
        EmpleadoDTO empleadoDTO = services.getEmpleado(id);
        if(empleadoDTO != null){
            return ResponseEntity.ok(empleadoDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> guardar(@RequestBody EmpleadoDTO empleadoDTO){
        services.guardarEmpleado(empleadoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Void> modificar(@RequestBody EmpleadoDTO empleadoDTO, @PathVariable int id){
        services.modificarEmpleado(empleadoDTO, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("empleados/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarEmpleado(id);
        return ResponseEntity.ok().build();
    }

}
