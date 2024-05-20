package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.models.dao.Empleado;
import com.project.libreria_backend.repository.EmpleadoRepository;
import com.project.libreria_backend.services.interfaces.IEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmpleadoImp implements IEmpleado {
    @Autowired
    private EmpleadoRepository repository;
    @Override
    public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = repository.findAll();
        if(!empleados.isEmpty()){
            return empleados;
        }else{
            return null;
        }
    }

    @Override
    public Empleado getEmpleado(int id) {
        Optional<Empleado> optionalEmpleado = repository.findById(id);
        if(optionalEmpleado.isPresent()){
            return optionalEmpleado.get();
        }
        else{
            throw new NoSuchElementException("No se encontró el empleado con ID: " + id);
        }
    }

    @Override
    public void guardarEmpleado(Empleado empleado) {
        repository.save(empleado);
    }

    @Override
    public void modificarEmpleado(Empleado empleado, int id) {
        Optional<Empleado> optionalEmpleado = repository.findById(id);
        if(optionalEmpleado.isPresent()){
            Empleado empleadoM = optionalEmpleado.get();
            empleadoM.setNombre(empleado.getNombre());
            empleadoM.setApellido(empleado.getApellido());
            empleadoM.setDni(empleado.getDni());
            empleadoM.setEstado(empleado.isEstado());
            repository.save(empleadoM);
        }
        else{
            throw new NoSuchElementException("No se encontró el empleado con ID: " + id);
        }
    }

    @Override
    public void eliminarEmpleado(int id) {
        Optional<Empleado> optionalEmpleado = repository.findById(id);
        if(optionalEmpleado.isPresent()){
            Empleado empleadoE = optionalEmpleado.get();
            empleadoE.setEstado(false);
            repository.save(empleadoE);
        }
        else{
            throw new NoSuchElementException("No se encontró el empleado con ID: " + id);
        }

    }
}
