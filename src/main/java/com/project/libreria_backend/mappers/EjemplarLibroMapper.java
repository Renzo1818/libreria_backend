package com.project.libreria_backend.mappers;


import com.project.libreria_backend.models.dao.EjemplarLibro;
import com.project.libreria_backend.models.dao.Libro;
import com.project.libreria_backend.models.dto.EjemplarLibroDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EjemplarLibroMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EjemplarLibroMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<EjemplarLibro, EjemplarLibroDTO>() {
            @Override
            protected void configure() {
                map().setId_libro(source.getLibro().getId_libro());
            }
        });
        modelMapper.addMappings(new PropertyMap<EjemplarLibroDTO, EjemplarLibro>() {
            @Override
            protected void configure() {
                using(context -> {
                    EjemplarLibroDTO dto = (EjemplarLibroDTO) context.getSource();
                    Integer idLibro = dto.getId_libro();
                    if (idLibro != null) {
                        Libro libro = new Libro();
                        libro.setId_libro(idLibro);
                        return libro;
                    } else {
                        return null;
                    }
                }).map(source, destination.getLibro());
            }
        });
    }

    public EjemplarLibroDTO convertirToDto(EjemplarLibro ejemplarLibro){
        return modelMapper.map(ejemplarLibro, EjemplarLibroDTO.class);
    }

    public EjemplarLibro convertirToEntity(EjemplarLibroDTO ejemplarLibroDTO){
        return modelMapper.map(ejemplarLibroDTO, EjemplarLibro.class);
    }
}
