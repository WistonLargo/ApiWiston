package com.example.apiwistonspring.services;

import com.example.apiwistonspring.dtos.MovilComparisionDTO;
import com.example.apiwistonspring.mappers.MovilComparisionDTOMapper;
import com.example.apiwistonspring.model.entities.Movil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComparisionServiceImpl implements ComparisionService {
    @Autowired
    private final MovilComparisionDTOMapper movilComparisionDTOMapper;

    public ComparisionServiceImpl(MovilComparisionDTOMapper movilComparisionDTOMapper) {
        this.movilComparisionDTOMapper = movilComparisionDTOMapper;
    }

    @Override
    public List<MovilComparisionDTO> compareTwoMoviles(Movil movilDTO1, Movil movilDTO2) {
        MovilComparisionDTO movilComparisionDTO1 = movilComparisionDTOMapper.mapToDTO(movilDTO1);
        MovilComparisionDTO movilComparisionDTO2 = movilComparisionDTOMapper.mapToDTO(movilDTO2);
        return List.of(movilComparisionDTO1, movilComparisionDTO2);
    }
}
