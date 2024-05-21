package com.project.libreria_backend.services.interfaces;


import com.project.libreria_backend.models.dto.EmpleadoDTO;

import java.util.List;

public interface IEmpleado {
    List<EmpleadoDTO> getAllEmpleados();
    EmpleadoDTO getEmpleado(int id);
    void guardarEmpleado(EmpleadoDTO empleadoDTO);
    void modificarEmpleado(EmpleadoDTO empleadoDTO, int id);
    void eliminarEmpleado(int id);
}
