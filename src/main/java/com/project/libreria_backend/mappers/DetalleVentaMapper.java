package com.project.libreria_backend.mappers;

import com.project.libreria_backend.models.dao.embedded_id.DetalleVenta;
import com.project.libreria_backend.models.dto.DetalleVentaDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public DetalleVentaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(DetalleVentaDTO.class, DetalleVenta.class)
                .addMappings(mapper -> {
                    mapper.skip(DetalleVenta::setVenta);
                    mapper.skip(DetalleVenta::setLibro);
                });
        modelMapper.addMappings(new PropertyMap<DetalleVenta, DetalleVentaDTO>() {
            @Override
            protected void configure() {
                map().setId_venta(source.getVenta().getId_venta());
                map().setId_libro(source.getLibro().getId_libro());
            }
        });
    }

    public DetalleVentaDTO convertirToDto(DetalleVenta detalleVenta){
        return modelMapper.map(detalleVenta, DetalleVentaDTO.class);
    }

    public DetalleVenta convertirToEntity(DetalleVentaDTO detalleVentaDTO) {
        DetalleVenta detalleVenta = modelMapper.map(detalleVentaDTO, DetalleVenta.class);
        return detalleVenta;
    }

}
