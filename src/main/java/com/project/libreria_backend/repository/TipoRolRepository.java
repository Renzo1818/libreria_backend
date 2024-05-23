package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRolRepository extends JpaRepository<TipoRol, Integer> {
}
