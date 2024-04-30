package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
