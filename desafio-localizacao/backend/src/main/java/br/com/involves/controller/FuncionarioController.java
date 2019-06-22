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

import br.com.involves.controller.dto.FuncionarioDTO;
import br.com.involves.controller.mapper.FuncionarioDTOMapper;
import br.com.involves.exception.EntityNotFoundException;
import br.com.involves.model.Funcionario;
import br.com.involves.service.funcionario.FuncionarioService;
import br.com.involves.util.Mensagens;

@RestController
public class FuncionarioController {

	private final FuncionarioService service;
	
	private final Logger logger = Logger.getLogger(FuncionarioController.class);
	
	private final Mensagens mensagens;
	
	@Autowired
	public FuncionarioController(final FuncionarioService service, final Mensagens mensagens) {
		this.service = service;
		this.mensagens = mensagens;
	}
	
	@GetMapping(value = "/funcionarios")
	public ResponseEntity<List<FuncionarioDTO>> getAll(){
		return ResponseEntity.ok(FuncionarioDTOMapper.makeDTOList(this.service.getAll()));
	}
	
	@GetMapping(value = "/funcionarios/{id}")
	public ResponseEntity<FuncionarioDTO> getOne(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(FuncionarioDTOMapper.makeDTO(this.service.getOne(id)));
		} catch (EntityNotFoundException e) {
			logger.error(mensagens.get("erro.service.funcionario.h2.naoEncontrado"));
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(value = "/funcionarios")
	public ResponseEntity<Object>  create(@RequestBody FuncionarioDTO funcionario) {
		Funcionario criado = this.service.create(FuncionarioDTOMapper.makeModel(funcionario));
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(criado.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/funcionarios/{id}")
	public ResponseEntity<FuncionarioDTO> update(@PathVariable Long id, @RequestBody FuncionarioDTO funcionario) {
		try {
			return ResponseEntity.ok(FuncionarioDTOMapper.makeDTO(this.service.update(id, FuncionarioDTOMapper.makeModel(funcionario))));
		} catch (EntityNotFoundException e) {
			logger.error(mensagens.get("erro.service.funcionario.h2.naoEncontrado"));
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(path ={"/funcionarios/{id}"})
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
