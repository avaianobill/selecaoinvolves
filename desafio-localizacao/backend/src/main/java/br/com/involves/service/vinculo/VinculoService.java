package br.com.involves.service.vinculo;

import br.com.involves.exception.EntityNotFoundException;

public interface VinculoService {

	void vincularLojas() throws EntityNotFoundException;
	
	void removerVinculos();
	
}
