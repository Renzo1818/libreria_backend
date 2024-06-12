package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.mappers.VentaMapper;
import com.project.libreria_backend.models.dao.Venta;
import com.project.libreria_backend.models.dto.VentaDTO;
import com.project.libreria_backend.repository.VentaRepository;
import com.project.libreria_backend.services.interfaces.ICarrito;
import com.project.libreria_backend.services.interfaces.IVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VentaImp implements IVenta {
    @Autowired
    private VentaRepository repository;
    @Autowired
    private VentaMapper mapper;
    @Autowired
    private ICarrito servicesCarrito;
    @Override
    public List<VentaDTO> getAllVentas() {
        List<Venta> lstVentas = repository.findAll();
        List<VentaDTO> ventaDTOS = new ArrayList<>();
        for(Venta venta: lstVentas){
            ventaDTOS.add(mapper.convertirToDto(venta));
        }
        return ventaDTOS;
    }

    @Override
    public VentaDTO getVenta(int id) {
        Optional<Venta> optionalVenta = repository.findById(id);
        if(optionalVenta.isPresent()){
            return mapper.convertirToDto(optionalVenta.get());
        }
        else{
            throw new NoSuchElementException("No se encontro la venta con el id: " + id);
        }
    }

    @Override
    public void agregarVenta(VentaDTO ventaDTO) {
        Venta venta = mapper.convertirToEntity(ventaDTO);
        venta.setImporte_total(servicesCarrito.calcularImporte());
        venta.setFecha_emision(new Date());
        repository.save(venta);
    }

    @Override
    public void eliminarVenta(int id) {
        Optional<Venta> optionalVenta = repository.findById(id);
        if(optionalVenta.isPresent()){
            Venta ventaE = optionalVenta.get();
            ventaE.setEstado(false);
            repository.save(ventaE);
        }
        else{
            throw new NoSuchElementException("No se encontro la venta con el id: " + id);
        }
    }
}
