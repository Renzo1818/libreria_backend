package com.project.libreria_backend.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TIPO_ROL")
@Setter @Getter
public class TipoRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL")
    private int id_rol;

    @Column(name = "ROL", length = 20)
    private String rol;

    @JsonIgnore
    @OneToMany(targetEntity = Usuario.class, mappedBy = "tipo", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public TipoRol() {
    }
}
