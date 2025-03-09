package com.example.apiwistonspring.services;

import com.example.apiwistonspring.dtos.MovilComparisionDTO;
import com.example.apiwistonspring.mappers.MovilComparisionDTOMapper;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComparisionServiceImpl implements ComparisionService {
    @Autowired
    private final MovilComparisionDTOMapper movilComparisionDTOMapper;
    @Autowired
    private final MovilRepository movilRepository;

    public ComparisionServiceImpl(MovilComparisionDTOMapper movilComparisionDTOMapper, MovilRepository movilRepository) {
        this.movilComparisionDTOMapper = movilComparisionDTOMapper;
        this.movilRepository = movilRepository;
    }

    @Override
    public List<MovilComparisionDTO> compareTwoMoviles(Long idMovil1, Long idMovil2) {
        Optional<Movil> movil1 = movilRepository.findById(idMovil1);
        Optional<Movil> movil2 = movilRepository.findById(idMovil2);
        if (movil1.isEmpty() || movil2.isEmpty()) {
            return List.of();
        }else{
            MovilComparisionDTO movilComparisionDTO1 = movilComparisionDTOMapper.mapToDTO(movil1.get());
            MovilComparisionDTO movilComparisionDTO2 = movilComparisionDTOMapper.mapToDTO(movil2.get());
            return List.of(movilComparisionDTO1, movilComparisionDTO2);
        }
    }
}
