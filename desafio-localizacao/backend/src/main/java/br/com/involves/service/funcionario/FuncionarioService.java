package br.com.involves.service.funcionario;

import java.util.List;

import br.com.involves.exception.EntityNotFoundException;
import br.com.involves.model.Funcionario;

public interface FuncionarioService {
	
	List<Funcionario> getAll();

	Funcionario getOne(Long id) throws EntityNotFoundException;

	Funcionario create(Funcionario funcionario);

	Funcionario update(Long id, Funcionario funcionario) throws EntityNotFoundException;

	Funcionario delete(long id) throws EntityNotFoundException;

}
