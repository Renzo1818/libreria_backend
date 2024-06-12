package com.project.libreria_backend.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.libreria_backend.models.dao.embedded_id.DetalleVenta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "VENTA")
@Getter @Setter
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTA")
    private int id_venta;

    @Column(name = "IMPORTE_TOTAL")
    private double importe_total;

    @Column(name = "FECHA_EMISION")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha_emision;

    @Column(name = "ESTADO")
    private boolean estado;

    @Column(name = "RUTA_BOLETA")
    private String ruta_boleta;

    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "ID_EMPLEADO", foreignKey = @ForeignKey(name = "FK_ID_EMPLEADO_VENTA"))
    private Empleado empleado;

    @JsonIgnore
    @OneToMany(targetEntity = DetalleVenta.class, mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;

    public Venta(){
    }
}
