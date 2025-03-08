package com.example.apiwistonspring.services;


import com.example.apiwistonspring.dtos.MovilComparisionDTO;
import com.example.apiwistonspring.dtos.MovilDTO;
import com.example.apiwistonspring.model.entities.Movil;

import java.util.List;

public interface ComparisionService {
    public List<MovilComparisionDTO> compareTwoMoviles(Movil movilDTO1, Movil movilDTO2);
}
