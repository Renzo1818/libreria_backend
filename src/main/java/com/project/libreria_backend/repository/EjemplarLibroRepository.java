package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.EjemplarLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarLibroRepository extends JpaRepository<EjemplarLibro, Integer> {
}
