package com.dev.introducaospring.dto.request;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import com.dev.introducaospring.domain.Aluno;

import lombok.Data;

@Data
public class AlunoDTORequest {

	private String nome;
	private String email;
	private String cpf;
	private String observacao;
	private String senha;

	public Aluno converter(AlunoDTORequest alunoDTO) {
		Aluno aluno = new Aluno();
		BeanUtils.copyProperties(alunoDTO, aluno);
		return aluno;
	}
	
	
}
