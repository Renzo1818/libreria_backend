package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.embedded_id.DetalleVenta;
import com.project.libreria_backend.models.dao.embedded_id.DetalleVentaKey;
import com.project.libreria_backend.models.dto.DetalleVentaConsolidadoDTO;
import com.project.libreria_backend.models.dto.DetalleVentaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaKey> {
    @Query("SELECT d FROM DetalleVenta d WHERE d.id.id_venta = :id_venta")
    List<DetalleVenta> getAllDetalleLibroById(@Param("id_venta") int id_venta);
    @Query("SELECT new com.project.libreria_backend.models.dto.DetalleVentaConsolidadoDTO(d.id.id_venta, d.id.id_libro, l.titulo, d.cantidad, d.importe) FROM DetalleVenta d JOIN d.libro l WHERE d.id.id_venta = :id_venta")
    List<DetalleVentaConsolidadoDTO> getAllConsolidadoDetalleLibroById(@Param("id_venta") int id_venta);


}
