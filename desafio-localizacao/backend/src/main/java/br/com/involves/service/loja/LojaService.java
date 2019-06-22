package br.com.involves.service.loja;

import java.util.List;

import br.com.involves.exception.EntityNotFoundException;
import br.com.involves.model.Loja;

public interface LojaService {

	List<Loja> getAll();

	Loja getOne(Long id) throws EntityNotFoundException;

	Loja create(Loja loja);

	Loja update(Long id, Loja loja) throws EntityNotFoundException;

	List<Loja> obterTodasAsLojasSemFuncionario();

	void delete(Loja criado);
	
	Loja delete(Long remover) throws EntityNotFoundException;

}
