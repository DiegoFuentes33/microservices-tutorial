package com.carroservice.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carroservice.entidades.Carro;
import com.carroservice.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	public List<Carro> getAll() {
		return carroRepository.findAll();
	}

	public Carro getCarroByid(int id) {
		return carroRepository.findById(id).orElse(null);
	}

	public Carro save(Carro carro) {
		Carro nuevoCarro = carroRepository.save(carro);
		return nuevoCarro;
	}

	public List<Carro> findByUsuarioId(int usuarioId) {
		return carroRepository.findByUsuarioid(usuarioId);
	}
}
