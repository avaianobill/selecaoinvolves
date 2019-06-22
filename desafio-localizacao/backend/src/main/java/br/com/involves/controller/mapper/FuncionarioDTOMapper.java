package br.com.involves.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.involves.controller.dto.FuncionarioDTO;
import br.com.involves.model.Funcionario;

public class FuncionarioDTOMapper {

	public static List<FuncionarioDTO> makeDTOList(List<Funcionario> funcionarios) {
		List<FuncionarioDTO> resultado = new ArrayList<>();
		if(funcionarios != null) {
			for(Funcionario model: funcionarios) {
				resultado.add(makeDTO(model));
			}
		}
		return resultado;
	}

	public static List<Funcionario> makeModelList(List<FuncionarioDTO> funcionarios) {
		List<Funcionario> resultado = new ArrayList<>();
		if(funcionarios != null) {
			for(FuncionarioDTO dto: funcionarios) {
				resultado.add(makeModel(dto));
			}
		}
		return resultado;
	}

	public static FuncionarioDTO makeDTO(Funcionario funcionario) {
		FuncionarioDTO dto = new FuncionarioDTO();
		dto.setId(funcionario.getId());
		dto.setName(funcionario.getName());
		dto.setLatitude(funcionario.getLatitude());
		dto.setLongitude(funcionario.getLongitude());
		dto.setLojas(LojaDTOMapper.makeDTOList(funcionario.getLojas()));
		return dto;
	}

	public static Funcionario makeModel(FuncionarioDTO funcionario) {
		Funcionario model = new Funcionario();
		model.setId(funcionario.getId());
		model.setName(funcionario.getName());
		model.setLatitude(funcionario.getLatitude());
		model.setLongitude(funcionario.getLongitude());
		model.setLojas(LojaDTOMapper.makeModelList(funcionario.getLojas()));
		return model;
	}

}
