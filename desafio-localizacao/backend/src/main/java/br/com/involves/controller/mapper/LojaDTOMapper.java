package br.com.involves.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.involves.controller.dto.LojaDTO;
import br.com.involves.model.Loja;

public class LojaDTOMapper {

	public static List<LojaDTO> makeDTOList(List<Loja> lojas) {
		List<LojaDTO> resultado = new ArrayList<>();
		if(lojas != null) {
			for(Loja loja: lojas) {
				resultado.add(makeDTO(loja));
			}
		}
		return resultado;
	}

	public static List<Loja> makeModelList(List<LojaDTO> lojas) {
		List<Loja> resultado = new ArrayList<>();
		if(lojas != null) {
			for(LojaDTO loja: lojas) {
				resultado.add(makeModel(loja));
			}
		}
		return resultado;
	}


	public static LojaDTO makeDTO(Loja loja) {
		LojaDTO dto = new LojaDTO();
		dto.setId(loja.getId());
		dto.setName(loja.getName());
		dto.setLatitude(loja.getLatitude());
		dto.setLongitude(loja.getLongitude());
		return dto;
	}

	public static Loja makeModel(LojaDTO funcionario) {
		Loja model = new Loja();
		model.setId(funcionario.getId());
		model.setName(funcionario.getName());
		model.setLatitude(funcionario.getLatitude());
		model.setLongitude(funcionario.getLongitude());
		return model;
	}

}
