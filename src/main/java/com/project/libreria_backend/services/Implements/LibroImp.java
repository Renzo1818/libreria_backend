package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.mappers.LibroMapper;
import com.project.libreria_backend.models.dao.Autor;
import com.project.libreria_backend.models.dao.Categoria;
import com.project.libreria_backend.models.dao.Editorial;
import com.project.libreria_backend.models.dao.Libro;
import com.project.libreria_backend.models.dto.LibroDTO;
import com.project.libreria_backend.repository.LibroRepository;
import com.project.libreria_backend.services.interfaces.ILIbro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LibroImp implements ILIbro {
    @Autowired
    private LibroRepository repository;
    @Autowired
    private LibroMapper mapper;
    @Override
    public List<LibroDTO> getAllLibros() {
        List<Libro> libros = repository.findAll();
        List<LibroDTO> libroDTOS = new ArrayList<>();
        for(Libro libro: libros){
            LibroDTO libroDTO = mapper.convertirToDto(libro);
            libroDTOS.add(libroDTO);
        }
        return libroDTOS;
    }

    @Override
    public LibroDTO getLibro(int id) {
        Optional<Libro> optionalLibro = repository.findById(id);
        if(optionalLibro.isPresent()){
            return mapper.convertirToDto(optionalLibro.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el libro con ID: " + id);
        }
    }

    @Override
    public void agregarLibro(LibroDTO libroDTO) {
        Libro libro = mapper.convertirToEntity(libroDTO);
        repository.save(libro);
    }

    @Override
    public void modificarLibro(LibroDTO libroDTO, int id) {
        Optional<Libro> optionalLibro = repository.findById(id);
        if(optionalLibro.isPresent()){
            Libro libro = optionalLibro.get();

            Autor autor = new Autor();
            autor.setId_autor(libroDTO.getId_autor());
            libro.setAutor(autor);

            Editorial editorial = new Editorial();
            editorial.setId_editorial(libroDTO.getId_editorial());
            libro.setEditorial(editorial);

            Categoria categoria = new Categoria();
            categoria.setId_categoria(libroDTO.getId_categoria());
            libro.setCategoria(categoria);

            libro.setTitulo(libroDTO.getTitulo());
            libro.setDescripcion(libroDTO.getDescripcion());
            libro.setPrecio_unitario(libroDTO.getPrecio_unitario());
            libro.setStock(libroDTO.getStock());
            libro.setFecha_publicacion(libroDTO.getFecha_publicacion());
            libro.setRuta_img(libroDTO.getRuta_img());
            libro.setEstado(libroDTO.isEstado());
            repository.save(libro);
        }
        else{
            throw new NoSuchElementException("No se encontró el libro con ID: " + id);
        }
    }

    @Override
    public void eliminarLibro(int id) {
        Optional<Libro> optionalLibro = repository.findById(id);
        if(optionalLibro.isPresent()){
            Libro libroE = optionalLibro.get();
            libroE.setEstado(false);
            repository.save(libroE);
        }
        else{
            throw new NoSuchElementException("No se encontró el libro con ID: " + id);
        }
    }
}
