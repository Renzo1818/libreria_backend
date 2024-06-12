package com.project.libreria_backend.mappers;

import com.project.libreria_backend.models.dao.Empleado;
import com.project.libreria_backend.models.dao.Venta;
import com.project.libreria_backend.models.dto.VentaDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public VentaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<Venta, VentaDTO>() {
            @Override
            protected void configure() {
                map().setId_empleado(source.getEmpleado().getId_empleado());
            }
        });
        modelMapper.addMappings(new PropertyMap<VentaDTO, Venta>() {
            @Override
            protected void configure() {
                using(context -> {
                    VentaDTO dto = (VentaDTO) context.getSource();
                    Integer idEmpleado = dto.getId_empleado();
                    if (idEmpleado != null) {
                        Empleado empleado = new Empleado();
                        empleado.setId_empleado(idEmpleado);
                        return empleado;
                    } else {
                        return null;
                    }
                }).map(source, destination.getEmpleado());
            }
        });
    }

    public VentaDTO convertirToDto(Venta venta){
        return modelMapper.map(venta, VentaDTO.class);
    }

    public Venta convertirToEntity(VentaDTO ventaDTO){
        return modelMapper.map(ventaDTO, Venta.class);
    }

}
