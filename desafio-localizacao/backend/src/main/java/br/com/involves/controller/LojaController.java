package br.com.involves.controller;

import java.net.URI;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.involves.controller.dto.LojaDTO;
import br.com.involves.controller.mapper.LojaDTOMapper;
import br.com.involves.exception.EntityNotFoundException;
import br.com.involves.model.Loja;
import br.com.involves.service.loja.LojaService;
import br.com.involves.service.vinculo.VinculoService;
import br.com.involves.util.Mensagens;

@RestController
public class LojaController {

	private final LojaService service;

	private final VinculoService vinculoService;

	private final Logger logger = Logger.getLogger(LojaController.class);

	private final Mensagens mensagens;

	@Autowired
	public LojaController(final LojaService service, final VinculoService vinculoService, final Mensagens mensagens) {
		this.service = service;
		this.vinculoService = vinculoService;
		this.mensagens = mensagens;
	}

	@GetMapping(value = "/lojas")
	public ResponseEntity<List<LojaDTO>> getAll(){
		return ResponseEntity.ok(LojaDTOMapper.makeDTOList(this.service.getAll()));
	}

	@GetMapping(value = "/lojas/{id}")
	public ResponseEntity<LojaDTO> getOne(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(LojaDTOMapper.makeDTO(this.service.getOne(id)));
		} catch (EntityNotFoundException e) {
			logger.error(mensagens.get("erro.service.loja.h2.naoEncontrado"));
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(value = "/lojas")
	public ResponseEntity<Object>  create(@RequestBody LojaDTO loja) {
		Loja criado = this.service.create(LojaDTOMapper.makeModel(loja));

		try {
			vinculoService.vincularLojas();
		} catch (EntityNotFoundException e) {
			this.service.delete(criado);
			logger.error(mensagens.get("erro.service.loja.h2.naoVinculada"));
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(criado.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/lojas/{id}")
	public ResponseEntity<LojaDTO> update(@PathVariable Long id, @RequestBody LojaDTO loja) {
		try {
			return ResponseEntity.ok(LojaDTOMapper.makeDTO(this.service.update(id, LojaDTOMapper.makeModel(loja))));
		} catch (EntityNotFoundException e) {
			logger.error(mensagens.get("erro.service.loja.h2.naoEncontrado"));
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path ={"/lojas/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		try {
			this.service.delete(id);
			return ResponseEntity.ok().build(); 
		} catch (EntityNotFoundException e) {
			logger.error(mensagens.get("erro.service.loja.h2.naoEncontrado"));
			return ResponseEntity.notFound().build();
		}
	}

}
