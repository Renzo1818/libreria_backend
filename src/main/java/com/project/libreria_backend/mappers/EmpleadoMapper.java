package com.project.libreria_backend.mappers;

import com.project.libreria_backend.models.dao.Empleado;
import com.project.libreria_backend.models.dto.EmpleadoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EmpleadoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    public EmpleadoDTO convertirToDto(Empleado empleado) {
        return modelMapper.map(empleado, EmpleadoDTO.class);
    }

    public Empleado convertirToEntity(EmpleadoDTO empleadoDTO) {
        return modelMapper.map(empleadoDTO, Empleado.class);
    }
}
