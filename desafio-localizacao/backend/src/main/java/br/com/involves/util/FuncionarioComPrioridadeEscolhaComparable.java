package br.com.involves.util;

import java.util.Comparator;

import br.com.involves.model.Funcionario;

public class FuncionarioComPrioridadeEscolhaComparable implements Comparator<Funcionario>{

	@Override
	public int compare(Funcionario o1, Funcionario o2) {
		return o1.compareTo(o2);
	}
	
	

}
