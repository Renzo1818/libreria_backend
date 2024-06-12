package com.project.libreria_backend.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "EMPLEADO", uniqueConstraints = {
        @UniqueConstraint(name="UK_DNI", columnNames = "DNI")
})
@Setter @Getter
public class Empleado {
    @Id
    @Column(name = "ID_EMPLEADO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empleado;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "APELLIDO", length = 50)
    private String apellido;

    @Column(name = "DNI", length = 8)
    private String dni;

    @Column(name = "ESTADO")
    private boolean estado;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(targetEntity = Venta.class, mappedBy = "empleado", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Venta> ventas;

    public Empleado() {
    }
}
