package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dao.Autor;

import java.util.List;

public interface IAutor {
    List<Autor> getAllAutores();
    Autor getAutor(int id);
    void guardarAutor(Autor autor);
    void modificarAutor(Autor autor, int id);
    void eliminar(int id);

}
