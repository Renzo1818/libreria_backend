package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dao.EjemplarLibro;
import com.project.libreria_backend.models.dto.EjemplarLibroDTO;

import java.util.List;

public interface IEjemplarLibro {
    List<EjemplarLibroDTO> getAllEjemplares();
    EjemplarLibroDTO getEjemplar(int id);
    void guardarEjemplar(EjemplarLibroDTO ejemplarLibroDTO);
    void modificarEjemplar(EjemplarLibroDTO ejemplarLibroDTOro, int id);
    void eliminarEjemplar(int id);
}
