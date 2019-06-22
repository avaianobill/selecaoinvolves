package br.com.involves.service.loja;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.involves.exception.EntityNotFoundException;
import br.com.involves.model.Loja;
import br.com.involves.repository.LojaRepository;
import br.com.involves.service.funcionario.H2FuncionarioService;
import br.com.involves.service.vinculo.H2VinculoService;
import br.com.involves.util.CVSLoader;
import br.com.involves.util.Mensagens;

@Service
public class H2LojaService implements LojaService {

	private final LojaRepository repository;

	private Logger logger = Logger.getLogger(H2FuncionarioService.class);

	private Mensagens mensagens;

	@Autowired
	public H2LojaService(final LojaRepository repository, final Mensagens mensagens) {
		this.repository = repository;
		this.mensagens = mensagens;
		
	}

	@PostConstruct 
	public void init() {

		logger.info(mensagens.get("info.service.loja.h2.inicializando"));
		
		List<Loja> lojas = new ArrayList<>();
		lojas.addAll(CVSLoader.loadObjectList(Loja.class, "lojas.cvs")); 

		StringBuilder sb = new StringBuilder(mensagens.get("info.service.loja.h2.resultado")).append("\n");
		
		for(Loja loja: lojas) {
			Loja lojaCriada = create(loja);
			sb.append("	 ").append(lojaCriada).append("\n");
		}
		logger.info(sb.toString());
	}


	@Override
	public List<Loja> getAll() {
		return (List<Loja>) repository.findAll();
	}

	@Override
	public Loja getOne(Long id) throws EntityNotFoundException {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(mensagens.get("erro.service.loja.h2.naoEncontrado", id)));
	}

	@Override
	public Loja create(Loja loja) {
		return repository.save(loja);
	}

	@Override
	public Loja update(Long id, Loja loja) throws EntityNotFoundException {
		return repository.findById(id).map(record -> {
			record.setLatitude(loja.getLatitude());
			record.setLongitude(loja.getLongitude());
			record.setFuncionario(loja.getFuncionario());
			repository.save(record);
			logger.info(mensagens.get("info.service.loja.h2.atualizado", id));
			return record;
		}).orElseThrow(() -> new EntityNotFoundException(mensagens.get("erro.service.loja.h2.naoEncontrado", id)));
	}

	@Override
	public List<Loja> obterTodasAsLojasSemFuncionario() {
		return this.repository.findAllByFuncionarioIsNull();
	}

	@Override
	public void delete(Loja criado) {
		this.repository.delete(criado);
	}

	@Override
	public Loja delete(Long remover) throws EntityNotFoundException {
		Optional<Loja> findById = repository.findById(remover);
		return findById.map(record -> {
            repository.deleteById(remover);
            return record;
        }).orElseThrow(() -> new EntityNotFoundException(mensagens.get("erro.service.loja.h2.naoEncontrado", remover)));
        
	}

}
