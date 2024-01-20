package com.motoservice.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoservice.entidades.Moto;
import com.motoservice.repository.MotoRepository;

@Service
public class MotoService {

	@Autowired
	private MotoRepository motoRepository;

	public List<Moto> getAll() {
		return motoRepository.findAll();
	}

	public Moto getMotoById(int id) {
		return motoRepository.findById(id).orElse(null);
	}

	public Moto save(Moto moto) {
		Moto nuevamoto = motoRepository.save(moto);
		return nuevamoto;
	}

	public List<Moto> findByUsuarioId(int usuarioid) {
		return motoRepository.findByUsuarioid(usuarioid);
	}
}
