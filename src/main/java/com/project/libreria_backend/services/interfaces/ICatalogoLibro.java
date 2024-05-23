package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.CatalogoLibroDTO;

import java.util.List;

public interface ICatalogoLibro {
    List<CatalogoLibroDTO> getCatalogoLibros();
    CatalogoLibroDTO getLibroByTitulo(String titulo);
    List<CatalogoLibroDTO> getLibroByNombreApellidoAutor(String nombreCompletoAutor);
    List<CatalogoLibroDTO> getLibroByCategoria(String categoria);
    List<CatalogoLibroDTO> getLibroByEditorial(String editorial);
    CatalogoLibroDTO getLibroByCatalogo(int idLibro);
}
