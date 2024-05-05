package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.models.dao.Categoria;
import com.project.libreria_backend.repository.CategoriaRepository;
import com.project.libreria_backend.services.interfaces.ICategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaImp implements ICategoria {
    @Autowired
    private CategoriaRepository repository;
    @Override
    public List<Categoria> getAllCategorias() {
        List<Categoria> lstCategorias = repository.findAll();
        if(!lstCategorias.isEmpty()){
            return lstCategorias;
        }
        return null;
    }
}
