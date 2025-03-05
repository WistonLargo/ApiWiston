package com.example.apiwistonspring.mappers;



import com.example.apiwistonspring.dtos.MovilDTO;
import com.example.apiwistonspring.model.entities.Movil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovilDTOMapper {
    MovilDTO mapToDTO(Movil movil);
    Movil mapToEntity(MovilDTO movilDTO);
}

