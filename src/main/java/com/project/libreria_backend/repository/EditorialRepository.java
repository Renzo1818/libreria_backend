package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
}
