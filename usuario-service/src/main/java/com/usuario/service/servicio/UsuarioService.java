package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CarroFeignClient carroFeignClient;
	
	@Autowired
	private MotoFeignClient motoFeignClient;
	
	public List<Carro> getCarros(int usuarioid){
		List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioid, List.class);
				return carros;
	}
	
	public List<Moto> getMotos(int usuarioid){
		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioid, List.class);
				return motos;
	}
	
	public Carro saveCarro(int usuarioid,Carro carro) {
		carro.setUsuarioid(usuarioid);
		Carro carroNuevo = carroFeignClient.save(carro);
		return carroNuevo;
		
	}
	
	public Moto saveMoto(int usuarioid, Moto moto) {
		moto.setUsuarioid(usuarioid);
		Moto motoNueva = motoFeignClient.save(moto);
		return motoNueva;
	}
	
	public Map<String, Object> getUsuarioAndVehiculos(int usuarioid){
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioid).orElse(null);
		
		if (usuario == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
		
		resultado.put("Usuario", usuario);
		List<Carro> carros = carroFeignClient.getCarros(usuarioid);
			if (carros.isEmpty()) {
				resultado.put("Carros", "El usuario no tiene carros");
			}
			else {
				resultado.put("Carros", carros);
			}
			
		List<Moto> motos = motoFeignClient.getMotos(usuarioid);
			if( motos.isEmpty()) {
				resultado.put("Motos", "El usuario no tiene motos");
			}
			else {
				resultado.put("Motos", motos);
			}
			return resultado;
		
	}

	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}
}

