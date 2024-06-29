package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    @Query("SELECT e FROM Empleado e ORDER BY e.id_empleado DESC LIMIT 1")
    Empleado findTopByOrderByIdEmpleado();
}
