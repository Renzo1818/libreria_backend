package com.project.libreria_backend.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.libreria_backend.models.dao.embedded_id.DetalleVenta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "LIBRO")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIBRO")
    private int id_libro;

    @Column(name = "TITULO",length = 200)
    private String titulo;

    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    @Column(name = "PRECIO_UNITARIO")
    private Double precio_unitario;

    @Column(name = "STOCK")
    private int stock;

    @Column(name = "ESTADO")
    private boolean estado;

    @Column(name = "FECHA_PUBLICACION")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_publicacion;

    @Column(name = "RUTA_IMG", length = 250)
    private String ruta_img;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "ID_CATEGORIA", foreignKey = @ForeignKey(name = "FK_ID_CATEGORIA"))
    private Categoria categoria;

    @ManyToOne(targetEntity = Autor.class)
    @JoinColumn(name = "ID_AUTOR", foreignKey = @ForeignKey(name = "FK_ID_AUTOR"))
    private Autor autor;

    @ManyToOne(targetEntity = Editorial.class)
    @JoinColumn(name = "ID_EDITORIAL", foreignKey = @ForeignKey(name = "FK_ID_EDITORIAL"))
    private Editorial editorial;

    @JsonIgnore
    @OneToMany(targetEntity = EjemplarLibro.class, mappedBy = "libro", cascade = CascadeType.ALL)
    private List<EjemplarLibro> ejemplarLibros;

    @JsonIgnore
    @OneToMany(targetEntity = DetalleVenta.class, mappedBy = "libro", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;

    public Libro(){
    }

}
