package com.example.apiwistonspring.populate;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.apiwistonspring.model.entities.Anuncio;
import com.example.apiwistonspring.model.entities.AnuncioVenta;
import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.entities.ERole;
import com.example.apiwistonspring.model.entities.Estado;
import com.example.apiwistonspring.model.entities.Marca;
import com.example.apiwistonspring.model.entities.Modelo;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.entities.Procesador;
import com.example.apiwistonspring.model.entities.RoleUser;
import com.example.apiwistonspring.model.entities.TecnologiaPantalla;
import com.example.apiwistonspring.model.entities.UserEntity;
import com.example.apiwistonspring.model.repositories.AnuncioRepository;
import com.example.apiwistonspring.model.repositories.DimensionesRepository;
import com.example.apiwistonspring.model.repositories.MarcaRepository;
import com.example.apiwistonspring.model.repositories.ModeloRepository;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import com.example.apiwistonspring.model.repositories.PantallaRepository;
import com.example.apiwistonspring.model.repositories.ProcesadorRepository;
import com.example.apiwistonspring.model.repositories.RoleRepository;
import com.example.apiwistonspring.model.repositories.TecnologiaPantallaRepository;
import com.example.apiwistonspring.model.repositories.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create", matchIfMissing = false)
public class Populate {
	private DimensionesRepository dimensionesRepository;
	private MarcaRepository marcaRepository;
	private ModeloRepository modeloRepository;
	private MovilRepository movilRepository;
	private PantallaRepository pantallaRepository;
	private ProcesadorRepository procesadorRepository;
	private TecnologiaPantallaRepository tecnologiaPantallaRepository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final AnuncioRepository anuncioRepository;

	public Populate(DimensionesRepository dimensionesRepository, MarcaRepository marcaRepository,
			ModeloRepository modeloRepository, MovilRepository movilRepository, PantallaRepository pantallaRepository,
			ProcesadorRepository procesadorRepository, TecnologiaPantallaRepository tecnologiaPantallaRepository,
			RoleRepository roleRepository, UserRepository userRepository, AnuncioRepository anuncioRepository) {
		super();
		this.dimensionesRepository = dimensionesRepository;
		this.marcaRepository = marcaRepository;
		this.modeloRepository = modeloRepository;
		this.movilRepository = movilRepository;
		this.pantallaRepository = pantallaRepository;
		this.procesadorRepository = procesadorRepository;
		this.tecnologiaPantallaRepository = tecnologiaPantallaRepository;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.anuncioRepository = anuncioRepository;
		populateRoles();
	}

	@Transactional
	@PostConstruct
	public void populate() {
		Marca marca1 = new Marca("Samsung");
		marcaRepository.save(marca1);
		Marca marca2 = new Marca("Apple");
		marcaRepository.save(marca2);
		Marca marca3 = new Marca("Xiaomi");
		marcaRepository.save(marca3);

		Dimensiones dimensiones1 = new Dimensiones(160.0, 75.0, 8.0);
		dimensionesRepository.save(dimensiones1);
		Dimensiones dimensiones2 = new Dimensiones(150.0, 70.0, 7.5);
		dimensionesRepository.save(dimensiones2);
		Dimensiones dimensiones3 = new Dimensiones(165.0, 78.0, 8.5);
		dimensionesRepository.save(dimensiones3);

		TecnologiaPantalla tecnologia1 = new TecnologiaPantalla(6.4, "AMOLED");
		tecnologiaPantallaRepository.save(tecnologia1);
		TecnologiaPantalla tecnologia2 = new TecnologiaPantalla(5.6, "LCD");
		tecnologiaPantallaRepository.save(tecnologia2);
		TecnologiaPantalla tecnologia3 = new TecnologiaPantalla(7.4, "OLED");
		tecnologiaPantallaRepository.save(tecnologia3);

		Pantalla pantalla1 = new Pantalla(tecnologia1, 6.5);
		pantallaRepository.save(pantalla1);
		Pantalla pantalla2 = new Pantalla(tecnologia2, 6.1);
		pantallaRepository.save(pantalla2);
		Pantalla pantalla3 = new Pantalla(tecnologia3, 6.7);
		pantallaRepository.save(pantalla3);

		Procesador procesador1 = new Procesador("Snapdragon 888", 8L, 4, 3.1);
		procesadorRepository.save(procesador1);
		Procesador procesador2 = new Procesador("A15 Bionic", 6L, 2, 2.5);
		procesadorRepository.save(procesador2);
		Procesador procesador3 = new Procesador("Dimensity 1200", 8L, 6, 3.5);
		procesadorRepository.save(procesador3);

		Modelo modelo1 = new Modelo(marca1, 1.5, "Galaxy S21");
		modeloRepository.save(modelo1);
		Modelo modelo2 = new Modelo(marca2, 2.6, "iPhone 13");
		modeloRepository.save(modelo2);
		Modelo modelo3 = new Modelo(marca3, 3.4, "Redmi Note 11");
		modeloRepository.save(modelo3);

		Movil movil1 = new Movil(128, dimensiones1, LocalDate.of(2022, 1, 1), 108, true, 180.0, 899.99, 90, 8, 100,
				modelo1, pantalla1, 10001L, procesador1);
		movilRepository.save(movil1);
		Movil movil2 = new Movil(64, dimensiones2, LocalDate.of(2021, 6, 15), 12, false, 160.0, 599.99, 80, 4, 200,
				modelo2, pantalla2, 10002L, procesador2);
		movilRepository.save(movil2);
		Movil movil3 = new Movil(256, dimensiones3, LocalDate.of(2023, 3, 1), 50, true, 190.0, 1099.99, 95, 6, 150,
				modelo3, pantalla3, 10003L, procesador3);
		movilRepository.save(movil3);
		Movil movil4 = new Movil(128, dimensiones1, LocalDate.of(2020, 8, 15), 48, true, 170.0, 799.99, 85, 6, 180,
				modelo1, pantalla3, 10004L, procesador1);
		movilRepository.save(movil4);
		Movil movil5 = new Movil(64, dimensiones2, LocalDate.of(2022, 11, 5), 64, false, 150.0, 499.99, 75, 4, 250,
				modelo2, pantalla1, 10005L, procesador2);
		movilRepository.save(movil5);
		Movil movil6 = new Movil(256, dimensiones1, LocalDate.of(2023, 1, 10), 108, true, 210.0, 1199.99, 100, 12, 300,
				modelo3, pantalla1, 10006L, procesador1);
		movilRepository.save(movil6);
		Movil movil7 = new Movil(32, dimensiones2, LocalDate.of(2020, 5, 25), 16, false, 140.0, 399.99, 65, 3, 120,
				modelo1, pantalla2, 10007L, procesador2);
		movilRepository.save(movil7);
		Movil movil8 = new Movil(128, dimensiones3, LocalDate.of(2022, 3, 20), 48, true, 180.0, 699.99, 85, 6, 220,
				modelo2, pantalla3, 10008L, procesador3);
		movilRepository.save(movil8);
		Movil movil9 = new Movil(256, dimensiones1, LocalDate.of(2021, 4, 8), 64, true, 200.0, 799.99, 90, 8, 190,
				modelo3, pantalla1, 10009L, procesador2);
		movilRepository.save(movil9);
		Movil movil10 = new Movil(64, dimensiones2, LocalDate.of(2019, 2, 14), 12, false, 130.0, 299.99, 60, 2, 140,
				modelo1, pantalla2, 10010L, procesador3);
		movilRepository.save(movil10);

		UserEntity usuario = new UserEntity("ivan@gmail.com", "ivan", "1234");
		userRepository.save(usuario);

	}

	public void populateRoles() {
		for (ERole erole : ERole.values()) {
			if (roleRepository.findByName(erole).isEmpty())
				roleRepository.save(new RoleUser(erole));
		}
	}
}
