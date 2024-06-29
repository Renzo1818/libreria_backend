package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.models.dao.Editorial;
import com.project.libreria_backend.repository.EditorialRepository;
import com.project.libreria_backend.services.interfaces.IEditorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Editorialmp implements IEditorial {
    @Autowired
    private EditorialRepository repository;

    @Override
    public List<Editorial> getAllEditoriales() {
        List<Editorial> lstEditorial = repository.findAll();
        if(!lstEditorial.isEmpty()){
            return lstEditorial;
        }
        return null;
    }

    @Override
    public Editorial getEditorial(int id) {
        Optional<Editorial> optionalEditorial = repository.findById(id);
        if(optionalEditorial.isPresent()){
            return optionalEditorial.get();
        }
        else{
            throw new NoSuchElementException("No se encontró la editorial con ID: " + id);
        }
    }

    @Override
    public void guardarEditorial(Editorial editorial) {
        repository.save(editorial);
    }

    @Override
    public void modificarEditorial(Editorial editorial, int id) {
        Optional<Editorial> optionalEditorial = repository.findById(id);
        if(optionalEditorial.isPresent()){
            Editorial editorialM = optionalEditorial.get();
            editorialM.setRazon_social(editorial.getRazon_social());
            editorialM.setRuc(editorial.getRuc());
            editorialM.setCorreo_electronico(editorial.getCorreo_electronico());
            editorialM.setEstado(editorial.isEstado());
            repository.save(editorialM);
        }
        else{
            throw new NoSuchElementException("No se encontró la editorial con ID: " + id);
        }
    }

    @Override
    public void eliminarEditorial(int id) {
        Optional<Editorial> optionalEditorial = repository.findById(id);
        if(optionalEditorial.isPresent()){
            Editorial editorialE = optionalEditorial.get();
            editorialE.setEstado(false);
            repository.save(editorialE);
        }
        else{
            throw new NoSuchElementException("No se encontró la editorial con ID: " + id);
        }
    }
}
