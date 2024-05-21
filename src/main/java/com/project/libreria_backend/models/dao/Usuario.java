package com.project.libreria_backend.models.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USUARIO", uniqueConstraints = @UniqueConstraint(name = "UK_MAIL", columnNames = "MAIL"))
@Getter @Setter
public class Usuario implements UserDetails {
    @Id
    @Column(name = "ID_USUARIO")
    private int id_usuario;

    @Column(name="MAIL", length = 40)
    private String mail;

    @Column(name = "CONTRASENA", length = 255)
    private String contrasena;

    @Column(name = "ESTADO")
    private boolean estado;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId
    @JoinColumn(name="ID_USUARIO", foreignKey = @ForeignKey(name = "FK_ID_USUARIO"))
    private Empleado empleado;

    @ManyToOne(targetEntity = TipoRol.class)
    @JoinColumn(name = "ID_TIPO_ROL", foreignKey = @ForeignKey(name = "FK_ID_TIPO_ROL"))
    private TipoRol tipo;

    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(tipo.getRol()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return mail;
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
        return estado;
    }
}
