package br.com.involves.service.vinculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import br.com.involves.exception.EntityNotFoundException;
import br.com.involves.model.Funcionario;
import br.com.involves.model.Loja;
import br.com.involves.service.funcionario.FuncionarioService;
import br.com.involves.service.loja.LojaService;
import br.com.involves.util.FuncionarioComPrioridadeEscolhaComparable;
import br.com.involves.util.HaversineFuncion;
import br.com.involves.util.Mensagens;

@Order(value=Ordered.LOWEST_PRECEDENCE)
@Service
public class H2VinculoService implements VinculoService {

	private Logger logger = Logger.getLogger(H2VinculoService.class);
	
	private FuncionarioService funcionarioService;
	private LojaService lojaService;
	private Mensagens mensagens;
		
	@Autowired
	public H2VinculoService(final FuncionarioService funcionarioService, final LojaService lojaService, final Mensagens mensagens) {
		this.funcionarioService = funcionarioService;
		this.lojaService = lojaService;
		this.mensagens = mensagens;
	}

	@Override
	public void vincularLojas() throws EntityNotFoundException {
		List<Funcionario> todosFuncionarios = this.funcionarioService.getAll(); 
		List<Loja> todasLojas = this.lojaService.obterTodasAsLojasSemFuncionario();

		ordernarFuncionarios(todosFuncionarios);
		List<Loja> copyOf = criarCopiaListaLojas(todasLojas);

		vincular(todosFuncionarios, copyOf);
		
		atualizarRegistrosFuncionarios(todosFuncionarios);

	}
	
	@Override
	public void removerVinculos() {
		logger.info(mensagens.get("info.service.vinculo.remocaoTodos.inicio"));
		
		funcionarioService.getAll().forEach(record -> {
			record.getLojas().clear();
			try {
				funcionarioService.update(record.getId(), record);
			} catch (EntityNotFoundException e) {
				// Não seria possível o lançamento dessa exceção nesse escopo, visto que 
				// iteração é feita sobre os registros recuperados do banco.
			}
		});
				
		lojaService.getAll().forEach(record -> {
			record.setFuncionario(null);
			try {
				lojaService.update(record.getId(), record);
			} catch (EntityNotFoundException e) {
				// Não seria possível o lançamento dessa exceção nesse escopo, visto que 
				// iteração é feita sobre os registros recuperados do banco.
			}
		});
		logger.info(mensagens.get("info.service.vinculo.remocaoTodos.fim"));
		
	}

	private void vincular(List<Funcionario> todosFuncionarios, List<Loja> copyOf) throws EntityNotFoundException {
		int tamanho = copyOf.size();
		int contadoAbort = 0;
		while(!copyOf.isEmpty() && contadoAbort < 3) {
			List<Loja> processadas = new ArrayList<>();
			for(Funcionario funcionario : todosFuncionarios) {
				for(Loja loja: copyOf) {
					double distancia = HaversineFuncion.haversine(funcionario.getLatitude(),
							funcionario.getLongitude(), loja.getLatitude(), loja.getLongitude());
					if(distancia <= 2 && loja.getFuncionario() == null) {
						vincularESalvar(processadas, funcionario, loja);
						break;
					} 
				}
			}
			copyOf.removeAll(processadas);
			if(copyOf.size() == tamanho) {
				contadoAbort++;
			}else {
				tamanho = copyOf.size();
			}
			
		}
	}

	private List<Loja> criarCopiaListaLojas(List<Loja> todasLojas) {
		return new ArrayList<Loja>(todasLojas);
	}

	private void ordernarFuncionarios(List<Funcionario> todosFuncionarios) {
		FuncionarioComPrioridadeEscolhaComparable comparador = new FuncionarioComPrioridadeEscolhaComparable();
		Collections.sort(todosFuncionarios, comparador);
		logger.info(mensagens.get("info.service.vinculo.funcionario.ordenar.menorNumeroLojas"));
	}

	private void vincularESalvar(List<Loja> processadas, Funcionario funcionario, Loja loja)
			throws EntityNotFoundException {
		funcionario.getLojas().add(loja); 
		loja.setFuncionario(funcionario);
		this.lojaService.update(loja.getId(), loja);
		processadas.add(loja);
		logger.info(mensagens.get("info.service.vinculo.lojas.atualizada", loja.getName(), funcionario.getName()));
	}

	private void atualizarRegistrosFuncionarios(List<Funcionario> todosFuncionarios) throws EntityNotFoundException {
		for(Funcionario func: todosFuncionarios) {
			this.funcionarioService.update(func.getId(), func);
		}
	}
	
}


