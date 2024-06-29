package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.mappers.DetalleConsolidadoMapper;
import com.project.libreria_backend.mappers.DetalleVentaMapper;
import com.project.libreria_backend.models.dao.Carrito;
import com.project.libreria_backend.models.dao.Libro;
import com.project.libreria_backend.models.dao.Venta;
import com.project.libreria_backend.models.dao.embedded_id.DetalleVenta;
import com.project.libreria_backend.models.dao.embedded_id.DetalleVentaKey;
import com.project.libreria_backend.models.dto.DetalleVentaConsolidadoDTO;
import com.project.libreria_backend.models.dto.DetalleVentaDTO;
import com.project.libreria_backend.repository.DetalleVentaRepository;
import com.project.libreria_backend.repository.LibroRepository;
import com.project.libreria_backend.repository.VentaRepository;
import com.project.libreria_backend.services.interfaces.ICarrito;
import com.project.libreria_backend.services.interfaces.IDetalleVenta;
import com.project.libreria_backend.services.interfaces.ILIbro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DetalleVentaImp implements IDetalleVenta {
    @Autowired
    private DetalleVentaRepository repository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private DetalleVentaMapper mapper;
    @Autowired
    private ICarrito servicesCarrito;
    @Autowired
    private LibroRepository repositoryLibro;
    @Autowired
    private ILIbro servicesLibro;
    @Autowired
    private DocxmergeImp servicesDocx;
    @Autowired
    private DetalleConsolidadoMapper consolidadoMapper;
    @Override
    public List<DetalleVentaDTO> getAllDetalleVenta() {
        List<DetalleVenta> detalleVentas = repository.findAll();
        List<DetalleVentaDTO> detalleVentaDTOS = new ArrayList<>();
        for(DetalleVenta detalleVenta : detalleVentas){
            DetalleVentaDTO detalleVentaDTO = mapper.convertirToDto(detalleVenta);
            detalleVentaDTOS.add(detalleVentaDTO);
        }
        return detalleVentaDTOS;

    }

    @Override
    public List<DetalleVentaDTO> getDetalleVenta(int id_venta) {
        List<DetalleVenta> detalleVentas = repository.getAllDetalleLibroById(id_venta);
        List<DetalleVentaDTO> detalleVentaDTOS = new ArrayList<>();
        for(DetalleVenta detalleVenta : detalleVentas){
            DetalleVentaDTO detalleVentaDTO = mapper.convertirToDto(detalleVenta);
            detalleVentaDTOS.add(detalleVentaDTO);
        }
        return detalleVentaDTOS;
    }
    @Override
    public void agregarDetalleVenta() {
        List<Carrito> items = servicesCarrito.getAllItems();
        Venta ultimaVentaRegistrado = ventaRepository.correlativoVenta();

        if (ultimaVentaRegistrado == null) {
            throw new IllegalStateException("No se pudo encontrar una venta registrada.");
        }
        for(Carrito carrito : items){
            DetalleVenta detalleVenta = new DetalleVenta();
            Optional<Libro> libro = repositoryLibro.findById(carrito.getId_libro());

            if (libro.isPresent()) {
                detalleVenta.setVenta(ultimaVentaRegistrado);
                detalleVenta.setLibro(libro.get());
                detalleVenta.setId(new DetalleVentaKey(ultimaVentaRegistrado.getId_venta(), libro.get().getId_libro()));
                detalleVenta.setCantidad(carrito.getCantidad());
                detalleVenta.setImporte(carrito.getImporte());

                repository.save(detalleVenta);

                servicesLibro.actualizarStock(libro.get().getId_libro(), carrito.getCantidad());
            } else {
                throw new NoSuchElementException("No se encontro el libro con ID: " + carrito.getId_libro());
            }
        }

        try {
            List<DetalleVentaConsolidadoDTO> detallesVentaConsolidadoDTOS = repository.getAllConsolidadoDetalleLibroById(ultimaVentaRegistrado.getId_venta());

            String outputFolderPath = "src/main/resources/templates";
            String outputFileName = "boleta_" + ultimaVentaRegistrado.getId_venta() + ".pdf";
            servicesDocx.generarBoleta(outputFolderPath, outputFileName, detallesVentaConsolidadoDTOS, ultimaVentaRegistrado.getId_venta());

            ultimaVentaRegistrado.setRuta_boleta(outputFolderPath + File.separator + outputFileName);
            ventaRepository.save(ultimaVentaRegistrado);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar la boleta", e);
        }

        servicesCarrito.limpiarCarrito();
    }
}
