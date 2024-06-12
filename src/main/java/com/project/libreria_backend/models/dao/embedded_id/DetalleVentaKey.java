package com.project.libreria_backend.models.dao.embedded_id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaKey implements Serializable {
    @Column(name = "ID_VENTA")
    private int id_venta;

    @Column(name = "ID_LIBRO")
    private int id_libro;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetalleVentaKey that = (DetalleVentaKey) o;

        if (id_venta != that.id_venta) return false;
        return id_libro == that.id_libro;
    }

    @Override
    public int hashCode() {
        int result = id_venta;
        result = 31 * result + id_libro;
        return result;
    }
}
