package br.com.involves.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.involves.exception.EntityNotFoundException;
import br.com.involves.service.vinculo.VinculoService;

@RestController
public class VinculoController {
		
	private VinculoService service;
	
	public VinculoController(@Autowired VinculoService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/vinculos", params = {"action"})
	public void vincular(@RequestParam(name = "action", defaultValue = "redefinir") String action) throws EntityNotFoundException  {
		this.service.removerVinculos();
		this.service.vincularLojas();
	}
	
}
