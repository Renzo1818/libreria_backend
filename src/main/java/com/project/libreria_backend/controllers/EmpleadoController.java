package com.project.libreria_backend.controllers;

import com.project.libreria_backend.models.dao.Empleado;
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
    public ResponseEntity<List<Empleado>> getAll(){
        return ResponseEntity.ok(services.getAllEmpleados());
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable int id){
        Empleado empleado = services.getEmpleado(id);
        if(empleado != null){
            return ResponseEntity.ok(empleado);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> guardar(@RequestBody Empleado empleado){
        services.guardarEmpleado(empleado);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Void> modificar(@RequestBody Empleado empleado, @PathVariable int id){
        services.modificarEmpleado(empleado, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("empleados/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        services.eliminarEmpleado(id);
        return ResponseEntity.ok().build();
    }

}
