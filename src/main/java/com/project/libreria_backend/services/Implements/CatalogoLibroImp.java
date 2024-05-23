package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.models.dto.CatalogoLibroDTO;
import com.project.libreria_backend.repository.LibroRepository;
import com.project.libreria_backend.services.interfaces.ICatalogoLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CatalogoLibroImp implements ICatalogoLibro {
    @Autowired
    private LibroRepository repository;

    @Override
    public List<CatalogoLibroDTO> getCatalogoLibros() {
        List<CatalogoLibroDTO> catalogoLibroDTOS = repository.findCatalogoLibros();
        if(!catalogoLibroDTOS.isEmpty()){
            return catalogoLibroDTOS;
        }
        return null;
    }

    @Override
    public CatalogoLibroDTO getLibroByTitulo(String titulo) {
        Optional<CatalogoLibroDTO> catalogoLibroDTO = repository.findLibroByTitulo(titulo);
        if(catalogoLibroDTO.isPresent()){
           return catalogoLibroDTO.get();
        }
        else{
            throw new NoSuchElementException("No se encontro el libro con titulo: " + titulo);
        }
    }

    @Override
    public List<CatalogoLibroDTO> getLibroByNombreApellidoAutor(String nombreCompletoAutor) {
        List<CatalogoLibroDTO> catalogoLibrosDTO = repository.findLibroByNombreApellidoAutor(nombreCompletoAutor);
        if(!catalogoLibrosDTO.isEmpty()){
            return catalogoLibrosDTO;
        }
        return null;
    }

    @Override
    public List<CatalogoLibroDTO> getLibroByCategoria(String categoria) {
        List<CatalogoLibroDTO> catalogoLibrosDTO = repository.findLibroByCategoria(categoria);
        if(!catalogoLibrosDTO.isEmpty()){
            return catalogoLibrosDTO;
        }
        return null;
    }

    @Override
    public List<CatalogoLibroDTO> getLibroByEditorial(String editorial) {
        List<CatalogoLibroDTO> catalogoLibrosDTO = repository.findLibroByEditorial(editorial);
        if(!catalogoLibrosDTO.isEmpty()){
            return catalogoLibrosDTO;
        }
        return null;
    }

    @Override
    public CatalogoLibroDTO getLibroByCatalogo(int idLibro) {
        Optional<CatalogoLibroDTO> catalogoLibroDTO = repository.findLibroByCatalogo(idLibro);
        if(catalogoLibroDTO.isPresent()){
            return catalogoLibroDTO.get();
        }
        else{
            throw new NoSuchElementException("No se encontro el libro con id: " + idLibro);
        }
    }
}
