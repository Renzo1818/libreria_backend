package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dto.UsuarioDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUsuario {
    List<UsuarioDTO> getAllUsuarios();
    UsuarioDTO getUsuario(int id);
    void guardarUsuario(UsuarioDTO usuarioDTO);
    void modificarUsuario(UsuarioDTO usuarioDTO, int id);
    void eliminarUsuario(int id);
}
