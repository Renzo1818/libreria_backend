package com.project.libreria_backend.services.interfaces;

import com.project.libreria_backend.models.dao.Editorial;

import java.util.List;

public interface IEditorial {
    List<Editorial> getAllEditoriales();
    Editorial getEditorial(int id);
    void guardarEditorial(Editorial editorial);
    void modificarEditorial(Editorial editorial, int id);
    void eliminarEditorial(int id);

}
