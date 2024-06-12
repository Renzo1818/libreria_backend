package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.LibroDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ILIbro {
    List<LibroDTO> getAllLibros();
    LibroDTO getLibro(int id);
    void agregarLibro(MultipartFile file, LibroDTO libroDTO);
    void modificarLibro(MultipartFile file, LibroDTO libroDTO, int id);
    void eliminarLibro(int id);
    void actualizarStock(int id, int cantidad);
}
