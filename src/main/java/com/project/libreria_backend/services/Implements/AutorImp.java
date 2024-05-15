package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.models.dao.Autor;
import com.project.libreria_backend.repository.AutorRepository;
import com.project.libreria_backend.services.interfaces.IAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutorImp implements IAutor {
    @Autowired
    private AutorRepository repository;
    @Override
    public List<Autor> getAllAutores() {
        List<Autor> lstAutor = repository.findAll();
        if(!lstAutor.isEmpty()){
            return lstAutor;
        }
        else{
            return null;
        }
    }
    @Override
    public Autor getAutor(int id) {
        Optional<Autor> optionalAutor = repository.findById(id);
        if(optionalAutor.isPresent()){
            return optionalAutor.get();
        }
        else{
            throw new NoSuchElementException("No se encontró el autor con ID: " + id);
        }
    }

    @Override
    public void guardarAutor(Autor autor) {
        repository.save(autor);

    }

    @Override
    public void modificarAutor(Autor autor, int id) {
        Optional<Autor> optionalAutor = repository.findById(id);
        if(optionalAutor.isPresent()){
            Autor autorM = optionalAutor.get();
            autorM.setNombre(autor.getNombre());
            autorM.setApellido(autor.getApellido());
            autorM.setFecha_nacimiento(autor.getFecha_nacimiento());
            autorM.setNacionalidad(autor.getNacionalidad());
            autorM.setEstado(autor.isEstado());
            repository.save(autorM);
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public void eliminar(int id) {
        Optional<Autor> optionalAutor = repository.findById(id);
        if(optionalAutor.isPresent()){
            Autor autorE = optionalAutor.get();
            autorE.setEstado(false);
            repository.save(autorE);
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }
}
