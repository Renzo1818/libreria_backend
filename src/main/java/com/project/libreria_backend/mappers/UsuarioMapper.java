package com.project.libreria_backend.mappers;

import com.project.libreria_backend.models.dao.Empleado;
import com.project.libreria_backend.models.dao.TipoRol;
import com.project.libreria_backend.models.dao.Usuario;
import com.project.libreria_backend.models.dto.UsuarioDTO;
import com.project.libreria_backend.repository.EmpleadoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<Usuario, UsuarioDTO>() {
            @Override
            protected void configure() {
                map().setId_rol(source.getTipo().getId_rol());
            }
        });
        modelMapper.addMappings(new PropertyMap<UsuarioDTO, Usuario>() {
            @Override
            protected void configure() {
                using(context -> {
                    UsuarioDTO dto = (UsuarioDTO) context.getSource();
                    Integer idTipoRol = dto.getId_rol();
                    if (idTipoRol != null) {
                        TipoRol tipoRol = new TipoRol();
                        tipoRol.setId_rol(idTipoRol);
                        return tipoRol;
                    } else {
                        return null;
                    }
                }).map(source, destination.getTipo());
            }
        });
        // Configuración para omitir el mapeo del campo Id_usuario
        modelMapper.typeMap(UsuarioDTO.class, Usuario.class)
                .addMappings(mapper -> mapper.skip(Usuario::setId_usuario));
    }

    public UsuarioDTO convertirToDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario convertirToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

        // Obtiene el último id_persona registrado
        Empleado ultimaEmpleadoRegistrado = empleadoRepository.findTopByOrderByIdEmpleado();

        usuario.setEmpleado(ultimaEmpleadoRegistrado); // Asigna el objeto Persona al Usuario

        // Genera el id_usuario con el mismo valor que el id_persona
        usuario.setId_usuario(ultimaEmpleadoRegistrado.getId_empleado());
        usuario.setContrasena(passwordEncoder.encode(usuarioDTO.getContrasena()));

        return usuario;
    }
}
