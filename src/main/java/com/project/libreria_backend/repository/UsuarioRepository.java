package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.Usuario;
import com.project.libreria_backend.models.dto.JoinEmpleadoUsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    Optional<Usuario> findByMail(@Param("mail") String mail);
    @Query("SELECT new com.project.libreria_backend.models.dto.JoinEmpleadoUsuarioDTO(e.nombre, e.apellido, t.rol) " +
            "FROM Usuario u INNER JOIN Empleado e ON u.id_usuario = e.id_empleado " +
            "INNER JOIN TipoRol t ON u.tipo.id_rol = t.id_rol " +
            "WHERE u.id_usuario = :idUsuario")
    Optional<JoinEmpleadoUsuarioDTO> findEmpleadoNombreApellidoRolById(@Param("idUsuario") int idUsuario);

    @Query("SELECT new com.project.libreria_backend.models.dto.JoinEmpleadoUsuarioDTO(e.id_empleado, u.mail, e.nombre, e.apellido, r.rol, u.estado) " +
            "FROM Usuario u " +
            "INNER JOIN Empleado e ON u.id_usuario = e.id_empleado " +
            "INNER JOIN TipoRol r ON u.tipo.id_rol = r.id_rol")
    List<JoinEmpleadoUsuarioDTO> findJoinEmpleadoUsuario();


}
