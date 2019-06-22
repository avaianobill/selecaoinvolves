package br.com.involves.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.involves.model.Loja;

@Repository
public interface LojaRepository extends CrudRepository<Loja, Long> {

	List<Loja> findAllByFuncionarioIsNull();

}
