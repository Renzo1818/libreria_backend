package com.project.libreria_backend.services.Implements;

import com.project.libreria_backend.mappers.EjemplarLibroMapper;
import com.project.libreria_backend.models.dao.EjemplarLibro;
import com.project.libreria_backend.models.dao.Libro;
import com.project.libreria_backend.models.dto.EjemplarLibroDTO;
import com.project.libreria_backend.repository.EjemplarLibroRepository;
import com.project.libreria_backend.repository.LibroRepository;
import com.project.libreria_backend.services.interfaces.IEjemplarLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EjemplarLibroImp implements IEjemplarLibro {
    @Autowired
    private EjemplarLibroRepository repository;
    @Autowired
    private LibroRepository repositoryLibro;
    @Autowired
    private EjemplarLibroMapper mapper;
    @Override
    public List<EjemplarLibroDTO> getAllEjemplares() {
        List<EjemplarLibro> ejemplarLibros = repository.findAll();
        List<EjemplarLibroDTO> ejemplarLibrosDTO = new ArrayList<>();
        for(EjemplarLibro ejemplarLibro : ejemplarLibros){
            EjemplarLibroDTO ejemplarLibroDTO = mapper.convertirToDto(ejemplarLibro);
            ejemplarLibrosDTO.add(ejemplarLibroDTO);
        }
        return ejemplarLibrosDTO;
    }

    @Override
    public EjemplarLibroDTO getEjemplar(int id) {
        Optional<EjemplarLibro> optionalEjemplarLibro = repository.findById(id);
        if(optionalEjemplarLibro.isPresent()){
            return mapper.convertirToDto(optionalEjemplarLibro.get());
        }
        else{
            throw new NoSuchElementException("No se encontró el ejemplar con ID: " + id);
        }
    }

    @Override
    public void guardarEjemplar(EjemplarLibroDTO ejemplarLibroDTO) {
        EjemplarLibro ejemplarLibro = mapper.convertirToEntity(ejemplarLibroDTO);
        repository.save(ejemplarLibro);

        Optional<Libro> optionalLibro = repositoryLibro.findById(ejemplarLibro.getLibro().getId_libro());
        if(optionalLibro.isPresent()){
            Libro libro  = optionalLibro.get();
            libro.setStock(libro.getStock() + ejemplarLibro.getNum_ingreso());
            repositoryLibro.save(libro);
        }
    }

    @Override
    public void modificarEjemplar(EjemplarLibroDTO ejemplarLibroDTO, int id) {
        Optional<EjemplarLibro> optionalEjemplarLibro = repository.findById(id);
        if(optionalEjemplarLibro.isPresent()){
            EjemplarLibro ejemplarLibro = optionalEjemplarLibro.get();

            Libro libroM = new Libro();
            libroM.setId_libro(ejemplarLibroDTO.getId_libro());


            Optional<Libro> optionalLibroR = repositoryLibro.findById(optionalEjemplarLibro.get().getLibro().getId_libro());
            if(optionalLibroR.isPresent()){
                Libro libro  = optionalLibroR.get();
                libro.setStock(libro.getStock() - optionalEjemplarLibro.get().getNum_ingreso());
                repositoryLibro.save(libro);
            }

            ejemplarLibro.setNum_ingreso(ejemplarLibroDTO.getNum_ingreso());

            Optional<Libro> optionalLibroS = repositoryLibro.findById(ejemplarLibro.getLibro().getId_libro());
            if(optionalLibroS.isPresent()){
                Libro libro  = optionalLibroS.get();
                libro.setStock(libro.getStock() + ejemplarLibro.getNum_ingreso());
                repositoryLibro.save(libro);
            }

            ejemplarLibro.setLibro(libroM);
            ejemplarLibro.setEstado_ejemplar(ejemplarLibroDTO.isEstado_ejemplar());
            repository.save(ejemplarLibro);
        }
        else{
            throw new NoSuchElementException("No se encontró el ejemplar con ID: " + id);
        }
    }

    /*@Override
    public void modificarEjemplar(EjemplarLibroDTO ejemplarLibroDTO, int id) {
        Optional<EjemplarLibro> optionalEjemplarLibro = repository.findById(id);
        if(optionalEjemplarLibro.isPresent()){
            EjemplarLibro ejemplarLibro = optionalEjemplarLibro.get();

            // Actualizar el libro del ejemplar si es necesario
            if (ejemplarLibroDTO.getId_libro() != ejemplarLibro.getLibro().getId_libro()) {
                Libro libroAntiguo = repositoryLibro.findById(ejemplarLibro.getLibro().getId_libro())
                        .orElseThrow(() -> new NoSuchElementException("No se encontró el libro anterior"));
                libroAntiguo.setStock(libroAntiguo.getStock() - ejemplarLibro.getNum_ingreso());
                repositoryLibro.save(libroAntiguo);

                Libro libroNuevo = repositoryLibro.findById(ejemplarLibroDTO.getId_libro())
                        .orElseThrow(() -> new NoSuchElementException("No se encontró el nuevo libro"));
                libroNuevo.setStock(libroNuevo.getStock() + ejemplarLibro.getNum_ingreso());
                repositoryLibro.save(libroNuevo);

                ejemplarLibro.setLibro(libroNuevo);
            }

            // Actualizar el número de ingreso y el estado del ejemplar
            ejemplarLibro.setNum_ingreso(ejemplarLibroDTO.getNum_ingreso());
            ejemplarLibro.setEstado_ejemplar(ejemplarLibroDTO.isEstado_ejemplar());
            repository.save(ejemplarLibro);
        }
        else{
            throw new NoSuchElementException("No se encontró el ejemplar con ID: " + id);
        }
    }*/


    @Override
    public void eliminarEjemplar(int id) {
        Optional<EjemplarLibro> optionalEjemplarLibro = repository.findById(id);
        if(optionalEjemplarLibro.isPresent()){
            repository.delete(optionalEjemplarLibro.get());

            Optional<Libro> optionalLibro = repositoryLibro.findById(optionalEjemplarLibro.get().getLibro().getId_libro());
            if(optionalLibro.isPresent()){
                Libro libro  = optionalLibro.get();
                libro.setStock(libro.getStock() - optionalEjemplarLibro.get().getNum_ingreso());
                repositoryLibro.save(libro);
            }
        }
        else{
            throw new NoSuchElementException("No se encontró el ejemplar con ID: " + id);
        }
    }
}
