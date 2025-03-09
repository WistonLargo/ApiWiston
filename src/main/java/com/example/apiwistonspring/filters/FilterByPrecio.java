package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.util.Intervalo;

public class FilterByPrecio extends Filter<Intervalo<Double>> {

    public FilterByPrecio(Intervalo<Double> parameter) {
        super(parameter);
    }

    @Override
    public boolean filter(Movil item) {
        return item.getPrecio() >= parameter.getMin() && item.getPrecio() <= parameter.getMax();
    }
}