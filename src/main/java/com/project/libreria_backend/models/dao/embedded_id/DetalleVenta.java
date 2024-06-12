package com.project.libreria_backend.models.dao.embedded_id;

import com.project.libreria_backend.models.dao.Libro;
import com.project.libreria_backend.models.dao.Venta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DETALLE_VENTA")
@Getter @Setter
public class DetalleVenta {
    @EmbeddedId
    private DetalleVentaKey id;

    @ManyToOne(targetEntity = Venta.class)
    @JoinColumn(name = "ID_VENTA", foreignKey = @ForeignKey(name = "FK_ID_VENTA_DETALLE"))
    @MapsId("id_venta")
    private Venta venta;

    @ManyToOne(targetEntity = Libro.class)
    @JoinColumn(name = "ID_LIBRO", foreignKey = @ForeignKey(name = "FK_ID_LIBRO_DETALLE"))
    @MapsId("id_libro")
    private Libro libro;

    @Column(name = "CANTIDAD")
    private int cantidad;

    @Column(name = "IMPORTE")
    private double importe;

    public DetalleVenta() {
    }

}
