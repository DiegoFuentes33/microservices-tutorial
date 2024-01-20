package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	 @Autowired
	 private UsuarioService usuarioService;
	 
	 @GetMapping
	 public ResponseEntity<List<Usuario>> listarUsuario(){
		 List<Usuario> usuarios = usuarioService.getAll();
		 	if(usuarios.isEmpty()) {
		 		return ResponseEntity.noContent().build();
		 	}
		 	return ResponseEntity.ok(usuarios);
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id){
		 Usuario usuario = usuarioService.getUsuarioById(id);
		 if(usuario == null) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok(usuario);
	 }
	 
	 @PostMapping
	 public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
	 	Usuario nuevoUsuario = usuarioService.save(usuario);
	 	return ResponseEntity.ok(nuevoUsuario);
	 }
	 
	 @GetMapping("/carros/{usuarioid}")
	 public ResponseEntity<List<Carro>> getCarros(@PathVariable ("usuarioid") int id){
		 Usuario usuario = usuarioService.getUsuarioById(id);
		 if (usuario == null) {
			 return ResponseEntity.notFound().build();
		 }
		 
		 List<Carro> carros = usuarioService.getCarros(id);
		 return ResponseEntity.ok(carros);
	 }
	 
	 @GetMapping("/motos/{usuarioid}")
	 public ResponseEntity<List<Moto>> getMotos(@PathVariable ("usuarioid") int id){
		 Usuario usuario = usuarioService.getUsuarioById(id);
		 if (usuario == null) {
			 return ResponseEntity.notFound().build();
		 }
		 
		 List<Moto> motos = usuarioService.getMotos(id);
		 return ResponseEntity.ok(motos);
	 }
	 
	 @PostMapping("/carro/{usuarioid}")
	 public ResponseEntity<Carro> guardarCarro(@PathVariable int usuarioid, @RequestBody Carro carro){
		 Carro nuevoCarro = usuarioService.saveCarro(usuarioid, carro);
		 return ResponseEntity.ok(nuevoCarro);
	 }
	 
	 @PostMapping("/moto/{usuarioid}")
	 public ResponseEntity<Moto> guardarMoto(@PathVariable int usuarioid, @RequestBody Moto moto){
		 Moto nuevaMoto = usuarioService.saveMoto(usuarioid, moto);
		 return ResponseEntity.ok(nuevaMoto);
	 }
	 
	 @GetMapping("/todos/{usuarioid}")
	 public ResponseEntity<Map<String, Object>> listarTodoslosVehiculos(@PathVariable int usuarioid){
		 Map<String, Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioid);
		 return ResponseEntity.ok(resultado);
	 }
	 
	 
	 
}
