package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.models.dao.TipoRol;
import com.project.libreria_backend.repository.TipoRolRepository;
import com.project.libreria_backend.services.interfaces.ITipoRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoRolImp implements ITipoRol {
    @Autowired
    private TipoRolRepository repository;
    @Override
    public List<TipoRol> getAllTipoRol() {
        List<TipoRol> lstRol = repository.findAll();
        if(!lstRol.isEmpty()){
            return lstRol;
        }
        return null;
    }
}
