package com.usuario.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.service.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
}
