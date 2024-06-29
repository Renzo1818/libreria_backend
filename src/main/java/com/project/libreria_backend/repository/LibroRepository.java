package com.project.libreria_backend.repository;

import com.project.libreria_backend.models.dao.Libro;
import com.project.libreria_backend.models.dto.CatalogoLibroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    @Query("SELECT new com.project.libreria_backend.models.dto.CatalogoLibroDTO(l.id_libro, l.titulo, CONCAT(a.nombre, ' ', a.apellido), c.descripcion, e.razon_social, l.stock, l.precio_unitario, l.estado, l.fecha_publicacion, l.descripcion, l.ruta_img) " +
            "FROM Libro l " +
            "INNER JOIN Autor a ON l.autor.id_autor = a.id_autor " +
            "INNER JOIN Categoria c ON l.categoria.id_categoria = c.id_categoria " +
            "INNER JOIN Editorial e ON l.editorial.id_editorial = e.id_editorial")
    List<CatalogoLibroDTO> findCatalogoLibros();
    @Query("SELECT new com.project.libreria_backend.models.dto.CatalogoLibroDTO(l.id_libro, l.titulo, CONCAT(a.nombre, ' ', a.apellido), c.descripcion, e.razon_social, l.stock, l.precio_unitario, l.estado, l.fecha_publicacion, l.descripcion, l.ruta_img) " +
            "FROM Libro l " +
            "INNER JOIN Autor a ON l.autor.id_autor = a.id_autor " +
            "INNER JOIN Categoria c ON l.categoria.id_categoria = c.id_categoria " +
            "INNER JOIN Editorial e ON l.editorial.id_editorial = e.id_editorial " +
            "WHERE l.titulo = :tituloLibro")
    Optional<CatalogoLibroDTO> findLibroByTitulo(@Param("tituloLibro") String tituloLibro);

    @Query("SELECT new com.project.libreria_backend.models.dto.CatalogoLibroDTO(l.id_libro, l.titulo, CONCAT(a.nombre, ' ', a.apellido), c.descripcion, e.razon_social, l.stock, l.precio_unitario, l.estado, l.fecha_publicacion, l.descripcion, l.ruta_img) " +
            "FROM Libro l " +
            "INNER JOIN Autor a ON l.autor.id_autor = a.id_autor " +
            "INNER JOIN Categoria c ON l.categoria.id_categoria = c.id_categoria " +
            "INNER JOIN Editorial e ON l.editorial.id_editorial = e.id_editorial " +
            "WHERE CONCAT(a.nombre, ' ', a.apellido) = :nombreCompletoAutor")
    List<CatalogoLibroDTO> findLibroByNombreApellidoAutor(@Param("nombreCompletoAutor") String nombreCompletoAutor);

    @Query("SELECT new com.project.libreria_backend.models.dto.CatalogoLibroDTO(l.id_libro, l.titulo, CONCAT(a.nombre, ' ', a.apellido), c.descripcion, e.razon_social, l.stock, l.precio_unitario, l.estado, l.fecha_publicacion, l.descripcion, l.ruta_img) " +
            "FROM Libro l " +
            "INNER JOIN Autor a ON l.autor.id_autor = a.id_autor " +
            "INNER JOIN Categoria c ON l.categoria.id_categoria = c.id_categoria " +
            "INNER JOIN Editorial e ON l.editorial.id_editorial = e.id_editorial " +
            "WHERE c.descripcion = :categoriaLibro")
    List<CatalogoLibroDTO> findLibroByCategoria(@Param("categoriaLibro") String categoriaLibro);

    @Query("SELECT new com.project.libreria_backend.models.dto.CatalogoLibroDTO(l.id_libro, l.titulo, CONCAT(a.nombre, ' ', a.apellido), c.descripcion, e.razon_social, l.stock, l.precio_unitario, l.estado, l.fecha_publicacion, l.descripcion, l.ruta_img) " +
            "FROM Libro l " +
            "INNER JOIN Autor a ON l.autor.id_autor = a.id_autor " +
            "INNER JOIN Categoria c ON l.categoria.id_categoria = c.id_categoria " +
            "INNER JOIN Editorial e ON l.editorial.id_editorial = e.id_editorial " +
            "WHERE e.razon_social = :editorialLibro")
    List<CatalogoLibroDTO> findLibroByEditorial(@Param("editorialLibro") String editorialLibro);

    @Query("SELECT new com.project.libreria_backend.models.dto.CatalogoLibroDTO(l.id_libro, l.titulo, CONCAT(a.nombre, ' ', a.apellido), c.descripcion, e.razon_social, l.stock, l.precio_unitario, l.estado, l.fecha_publicacion, l.descripcion, l.ruta_img) " +
            "FROM Libro l " +
            "INNER JOIN Autor a ON l.autor.id_autor = a.id_autor " +
            "INNER JOIN Categoria c ON l.categoria.id_categoria = c.id_categoria " +
            "INNER JOIN Editorial e ON l.editorial.id_editorial = e.id_editorial " +
            "WHERE l.id_libro = :idLibro")
    Optional<CatalogoLibroDTO> findLibroByCatalogo(@Param("idLibro") int idLibro);


}
