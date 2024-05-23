package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.JoinEmpleadoUsuarioDTO;

import java.util.List;

public interface ICatalogoUsuario {
    List<JoinEmpleadoUsuarioDTO> getCatalogoUsuario();
}
