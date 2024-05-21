package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.jwt.JwtService;
import com.project.libreria_backend.models.dao.Usuario;
import com.project.libreria_backend.models.dto.AuthResponse;
import com.project.libreria_backend.models.dto.CredentialsDTO;
import com.project.libreria_backend.models.dto.JoinEmpleadoUsuarioDTO;
import com.project.libreria_backend.repository.UsuarioRepository;
import com.project.libreria_backend.services.interfaces.IAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthImp implements IAuth {
    private final UsuarioRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse login(CredentialsDTO credentialsDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentialsDTO.getMail(), credentialsDTO.getContrasena()));
        Usuario usuario = repository.findByMail(credentialsDTO.getMail()).orElseThrow();
        Optional<JoinEmpleadoUsuarioDTO> joinEmpleadoUsuarioDTO = repository.findEmpleadoNombreApellidoRolById(usuario.getId_usuario());
        UserDetails user = (UserDetails) repository.findByMail(credentialsDTO.getMail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .id_usuario(usuario.getId_usuario())
                .nombre(joinEmpleadoUsuarioDTO.get().getNombre())
                .apellido(joinEmpleadoUsuarioDTO.get().getApellido())
                .rol(joinEmpleadoUsuarioDTO.get().getRol())
                .token(token)
                .build();
    }
}
