package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.mappers.UsuarioMapper;
import com.project.libreria_backend.models.dao.Empleado;
import com.project.libreria_backend.models.dao.TipoRol;
import com.project.libreria_backend.models.dao.Usuario;
import com.project.libreria_backend.models.dto.UsuarioDTO;
import com.project.libreria_backend.repository.UsuarioRepository;
import com.project.libreria_backend.services.interfaces.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioImp implements IUsuario, UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios =  repository.findAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for(Usuario usuario: usuarios){
            UsuarioDTO usuarioDTO = mapper.convertirToDto(usuario);
            usuarioDTOS.add(usuarioDTO);
        }
        return usuarioDTOS;
    }

    @Override
    public UsuarioDTO getUsuario(int id) {
        Optional<Usuario> optionalUsuario = repository.findById(id);

        if(optionalUsuario.isPresent()){
            Usuario usuarioB = optionalUsuario.get();
            return mapper.convertirToDto(usuarioB);
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public void guardarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioR = mapper.convertirToEntity(usuarioDTO);
        repository.save(usuarioR);
    }

    @Override
    public void modificarUsuario(UsuarioDTO usuarioDTO, int id) {
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if(optionalUsuario.isPresent()){
            Usuario usuarioM = optionalUsuario.get();
            usuarioM.setMail(usuarioDTO.getMail());
            usuarioM.setContrasena(passwordEncoder.encode(usuarioDTO.getContrasena()));
            usuarioM.setEstado(usuarioDTO.isEstado());

            TipoRol tipoRol = new TipoRol();
            tipoRol.setId_rol(usuarioDTO.getId_rol());
            usuarioM.setTipo(tipoRol);

            Empleado empleado = new Empleado();
            empleado.setId_empleado(usuarioDTO.getId_usuario());
            usuarioM.setEmpleado(empleado);

            repository.save(usuarioM);
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if(optionalUsuario.isPresent()){
            Usuario usuarioE = optionalUsuario.get();
            usuarioE.setEstado(false);

            repository.save(usuarioE);
        }
        else{
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario =  repository
                .findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con correo electronico" + mail + "no existe"));
        return usuario;
    }

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(usuario.getTipo().getRol()));
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }*/
}
