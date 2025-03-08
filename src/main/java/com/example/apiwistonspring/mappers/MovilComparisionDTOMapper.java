package com.example.apiwistonspring.mappers;

import com.example.apiwistonspring.dtos.MovilComparisionDTO;
import com.example.apiwistonspring.model.entities.Movil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovilComparisionDTOMapper {
    MovilComparisionDTO mapToDTO(Movil movil);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaLanzamiento", ignore = true)
    @Mapping(target = "referencia", ignore = true)
    Movil mapToEntity(MovilComparisionDTO movilComparisionDTO);
}
