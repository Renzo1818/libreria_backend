package com.project.libreria_backend.services.interfaces;


import com.project.libreria_backend.models.dao.Empleado;

import java.util.List;

public interface IEmpleado {
    List<Empleado> getAllEmpleados();
    Empleado getEmpleado(int id);
    void guardarEmpleado(Empleado empleado);
    void modificarEmpleado(Empleado empleado, int id);
    void eliminarEmpleado(int id);
}
