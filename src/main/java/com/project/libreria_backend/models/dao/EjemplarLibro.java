package com.project.libreria_backend.models.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "EJEMPLAR_LIBRO")
public class EjemplarLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EJEMPLAR")
    private int id_ejemplar;

    @Column(name = "NUM_INGRESO")
    private int num_ingreso;

    @Column(name = "ESTADO_EJEMPLAR")
    private boolean estado_ejemplar;

    @ManyToOne(targetEntity = Libro.class)
    @JoinColumn(name = "ID_LIBRO", foreignKey = @ForeignKey(name = "FK_ID_LIBRO_EJEMPLAR"))
    private Libro libro;
    public EjemplarLibro(){
    }

}
