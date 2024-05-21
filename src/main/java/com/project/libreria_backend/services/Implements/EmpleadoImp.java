package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.mappers.EmpleadoMapper;
import com.project.libreria_backend.models.dao.Empleado;
import com.project.libreria_backend.models.dao.Libro;
import com.project.libreria_backend.models.dto.EmpleadoDTO;
import com.project.libreria_backend.models.dto.LibroDTO;
import com.project.libreria_backend.repository.EmpleadoRepository;
import com.project.libreria_backend.services.interfaces.IEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmpleadoImp implements IEmpleado {
    @Autowired
    private EmpleadoRepository repository;
    @Autowired
    private EmpleadoMapper mapper;
    @Override
    public List<EmpleadoDTO> getAllEmpleados() {
        List<Empleado> empleados = repository.findAll();
        List<EmpleadoDTO> empleadoDTOS = new ArrayList<>();
        for(Empleado empleado: empleados){
            EmpleadoDTO empleadoDTO = mapper.convertirToDto(empleado);
            empleadoDTOS.add(empleadoDTO);
        }
        return empleadoDTOS;
    }

    @Override
    public EmpleadoDTO getEmpleado(int id) {
        Optional<Empleado> optionalEmpleado = repository.findById(id);
        if(optionalEmpleado.isPresent()){
            return mapper.convertirToDto(optionalEmpleado.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el empleado con ID: " + id);
        }
    }

    @Override
    public void guardarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = mapper.convertirToEntity(empleadoDTO);
        repository.save(empleado);
    }

    @Override
    public void modificarEmpleado(EmpleadoDTO empleadoDTO, int id) {
        Optional<Empleado> optionalEmpleado = repository.findById(id);
        if(optionalEmpleado.isPresent()){
            Empleado empleadoM = optionalEmpleado.get();
            empleadoM.setNombre(empleadoDTO.getNombre());
            empleadoM.setApellido(empleadoDTO.getApellido());
            empleadoM.setDni(empleadoDTO.getDni());
            empleadoM.setEstado(empleadoDTO.isEstado());
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
