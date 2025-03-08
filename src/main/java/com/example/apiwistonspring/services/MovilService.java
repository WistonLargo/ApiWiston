package com.example.apiwistonspring.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiwistonspring.dtos.FilterDTO;
import com.example.apiwistonspring.dtos.MovilDTO;
import com.example.apiwistonspring.filters.Filter;
import com.example.apiwistonspring.mappers.MapDTOFilterToListFilter;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.repositories.MovilRepository;

@Service
public class MovilService implements InterfazMovilService {

	@Autowired
	private MovilRepository movilRepository;
	private MapDTOFilterToListFilter filterMapper;

	/**
	 * Obtiene los 5 móviles en tendencia según su popularidad.
	 * 
	 * @return Lista con los 5 móviles más populares.
	 */
	public List<Movil> obtenerMovilesEnTendencia() {
		return movilRepository.findTop5ByOrderByPuntuacionDesc();
	}

	public Movil saveMovil(Movil movil) {
		return movilRepository.save(movil);
	}

	public List<Movil> getAllMoviles() {
		return movilRepository.findAll();
	}

	public Optional<Movil> getMovilById(Long id) {
		return movilRepository.findById(id);
	}

	public Movil updateMovil(Long id, Movil updatedMovil) {
		Optional<Movil> existingMovil = movilRepository.findById(id);
		if (existingMovil.isPresent()) {
			Movil movilToUpdate = existingMovil.get();
			// Actualiza solo los campos que quieres cambiar.
			movilToUpdate.setAlmacenamiento(updatedMovil.getAlmacenamiento());
			movilToUpdate.setDimensiones(updatedMovil.getDimensiones());
			movilToUpdate.setFechaLanzamiento(updatedMovil.getFechaLanzamiento());
			movilToUpdate.setMegaPixeles(updatedMovil.getMegaPixeles());
			movilToUpdate.setNfc(updatedMovil.isNfc());
			movilToUpdate.setPeso(updatedMovil.getPeso());
			movilToUpdate.setPrecio(updatedMovil.getPrecio());
			movilToUpdate.setPuntuacion(updatedMovil.getPuntuacion());
			movilToUpdate.setRam(updatedMovil.getRam());
			movilToUpdate.setModelo(updatedMovil.getModelo());
			movilToUpdate.setPantalla(updatedMovil.getPantalla());
			movilToUpdate.setReferencia(updatedMovil.getReferencia());
			movilToUpdate.setProcesador(updatedMovil.getProcesador());

			// Guarda el móvil actualizado
			return movilRepository.save(movilToUpdate);
		} else {
			throw new RuntimeException("Móvil no encontrado con el ID: " + id);
		}
	}

	public void deleteMovil(Long id) {
		if (!movilRepository.existsById(id)) {
			throw new RuntimeException("Móvil no encontrado con el ID: " + id);
		}
		movilRepository.deleteById(id);
	}

	public List<Movil> filterMoviles(FilterDTO filterDTO) {
		List<Filter<?>> filtros = filterMapper.map(filterDTO);

		List<Movil> moviles = movilRepository.findAll();

		for (Filter<?> filtro : filtros) {
			moviles = moviles.stream().filter(movil -> filtro.filter(movil)).collect(Collectors.toList());
		}

		return moviles;

	}
}
