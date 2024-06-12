package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("SELECT v FROM Venta v ORDER BY v.id_venta DESC LIMIT 1")
    Venta correlativoVenta();
}
