package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.models.dto.JoinEmpleadoUsuarioDTO;
import com.project.libreria_backend.repository.UsuarioRepository;
import com.project.libreria_backend.services.interfaces.ICatalogoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoUsuario implements ICatalogoUsuario {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<JoinEmpleadoUsuarioDTO> getCatalogoUsuario() {
        List<JoinEmpleadoUsuarioDTO> catalogoUsuariosDTOS = repository.findJoinEmpleadoUsuario();
        if(!catalogoUsuariosDTOS.isEmpty()){
            return catalogoUsuariosDTOS;
        }
        return null;
    }
}
