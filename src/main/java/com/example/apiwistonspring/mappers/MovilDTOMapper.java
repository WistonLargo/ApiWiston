package com.example.apiwistonspring.mappers;


import org.springframework.web.bind.annotation.Mapping;

import com.example.apiwistonspring.dtos.MovilDTO;
import com.example.apiwistonspring.model.entities.Movil;

@Mapper(componentModel = "spring")
public interface MovilDTOMapper {
    MovilDTO mapToDTO(Movil movil); 
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "remember", ignore = true)
    Movil mapToEntity(MovilDTO movilDTO);
}

