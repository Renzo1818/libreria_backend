package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.LibroDTO;

import java.util.List;

public interface ILIbro {
    List<LibroDTO> getAllLibros();
    LibroDTO getLibro(int id);
    void agregarLibro(LibroDTO libroDTO);
    void modificarLibro(LibroDTO libroDTO, int id);
    void eliminarLibro(int id);
}
