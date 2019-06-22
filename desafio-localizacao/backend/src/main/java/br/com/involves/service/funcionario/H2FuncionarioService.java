package br.com.involves.service.funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import br.com.involves.exception.EntityNotFoundException;
import br.com.involves.model.Funcionario;
import br.com.involves.repository.FuncionarioRepository;
import br.com.involves.util.CVSLoader;
import br.com.involves.util.Mensagens;

@Order(value=Ordered.HIGHEST_PRECEDENCE)
@Service
public class H2FuncionarioService implements FuncionarioService {

	private final FuncionarioRepository repository;

	private Logger logger = Logger.getLogger(H2FuncionarioService.class);
	
	private Mensagens mensagens;
	
	@Autowired
	public H2FuncionarioService(final FuncionarioRepository repository, final Mensagens mensagens) {
		this.repository = repository;
		this.mensagens = mensagens;
	}

	
	@Override
	public List<Funcionario> getAll() {
		logger.info(mensagens.get("info.service.funcionario.h2.obterTodos"));
		return (List<Funcionario>) repository.findAll();
	}

	@Override
	public Funcionario getOne(Long id) throws EntityNotFoundException {
		logger.info(mensagens.get("info.service.funcionario.h2.obterPorId", id));
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(mensagens.get("erro.service.loja.h2.naoEncontrado", id)));
	}

	@Override
	public Funcionario create(Funcionario funcionario) {
		return repository.save(funcionario);
	}

	@Override
	public Funcionario update(Long id, Funcionario funcionario) throws EntityNotFoundException {
		return repository.findById(id).map(record -> {
			record.setLatitude(funcionario.getLatitude());
			record.setLongitude(funcionario.getLongitude());
			record.setLojas(funcionario.getLojas());
			repository.save(record);
			logger.info(mensagens.get("info.service.funcionario.h2.atualizado", id));
			return record;
		}).orElseThrow(() -> 
			new EntityNotFoundException(mensagens.get("erro.service.funcionario.h2.naoEncontrado", id))
		);
	}

	@Override
	public Funcionario delete(long id) throws EntityNotFoundException {
		Optional<Funcionario> findById = repository.findById(id);
		return findById.map(record -> {
            repository.deleteById(id);
            return record;
        }).orElseThrow(() -> new EntityNotFoundException(mensagens.get("erro.service.funcionario.h2.naoEncontrado", id)));
        
		
	}

}
