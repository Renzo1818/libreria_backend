package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dao.Carrito;

import java.util.List;

public interface ICarrito {
    List<Carrito> getAllItems();
    void agregarItemCarrito(Carrito carrito);
    void modificarCantidadItemCarrito(Carrito carrito, int id);
    void eliminarItemCarrito(int id);
    void limpiarCarrito();
    double calcularImporte();

}
