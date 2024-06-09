package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.models.dao.Carrito;
import com.project.libreria_backend.services.interfaces.ICarrito;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarritoImp implements ICarrito {
    List<Carrito> lstCarrito = new ArrayList<>();
    @Override
    public List<Carrito> getAllItems() {
        List<Carrito> lst = lstCarrito;
        if(!lst.isEmpty()){
            return lst.stream().toList();
        }
        else{
            return null;
        }
    }
    /*@Override
    public void agregarItemCarrito(Carrito carrito) {
        if(carrito != null && carrito.getCantidad() > 0){
            List<Carrito> lst = lstCarrito;
            for(Carrito item : lst){
                if(item.getId_libro() == carrito.getId_libro()){
                    item.setCantidad(item.getCantidad() + carrito.getCantidad());
                    break;
                }
            }
            lstCarrito.add(carrito);
        }else{
            throw new NoSuchElementException("No se puede agregar un item nulo: " + null);
        }

    }*/

    public void agregarItemCarrito(Carrito carrito) {
        if (carrito != null && carrito.getCantidad() > 0) {
            boolean libroExiste = false;
            for (Carrito item : lstCarrito) {
                if (item.getId_libro() == carrito.getId_libro()) {
                    libroExiste = true;
                    int nuevaCantidad = item.getCantidad() + carrito.getCantidad();
                    if (nuevaCantidad > 0) {
                        item.setCantidad(nuevaCantidad);
                    } else {
                        lstCarrito.remove(item);
                    }
                    break;
                }
            }
            if (!libroExiste) {
                lstCarrito.add(carrito);
            }
        } else {
            throw new NoSuchElementException("No se puede agregar un item nulo o con cantidad 0: " + carrito);
        }
    }

    @Override
    public void modificarCantidadItemCarrito(Carrito carrito, int id) {
        if(carrito != null && carrito.getCantidad() > 0){
            for(Carrito item: lstCarrito){
                if(item.getId_libro() == carrito.getId_libro()){
                    item.setCantidad(carrito.getCantidad());
                    break;
                }
            }


        }else{
            throw new NoSuchElementException("No se puede agregar o disminuir un item nulo o con cantidad 0: " + carrito);
        }
    }

    @Override
    public void eliminarItemCarrito(int id) {
        if(id != 0){
            for (Carrito item : lstCarrito) {
                if (item.getId_libro() == id) {
                    lstCarrito.remove(item);
                    break;
                }
            }
        }
        else{
            throw new NoSuchElementException("No existe un item con el id: " + id);
        }

    }

    @Override
    public void limpiarCarrito() {
        lstCarrito.clear();
    }

    @Override
    public double calcularImporte(){
        if(!lstCarrito.isEmpty()){
            double acumuladorTotal = 0.0;
            for(Carrito item: lstCarrito){
                acumuladorTotal += item.getImporte();
            }
            return acumuladorTotal;
        }
        else{
            return 0.0;
        }
    }
}
