package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.models.Carro;

@FeignClient(name = "carro-service", url = "http://localhost:8002")
public interface CarroFeignClient {
	
	
	@PostMapping	
	public Carro save(@RequestBody Carro carro);
	
	@GetMapping("/usuario/{usuarioid}")
	public List<Carro> getCarros(@PathVariable int usuarioid);
}
