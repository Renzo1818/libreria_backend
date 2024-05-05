package com.project.libreria_backend.services.interfaces;


import com.project.libreria_backend.models.dao.Categoria;

import java.util.List;

public interface ICategoria {
    List<Categoria> getAllCategorias();
}
