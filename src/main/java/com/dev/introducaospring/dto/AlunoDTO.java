package com.dev.introducaospring.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import com.dev.introducaospring.domain.Aluno;

import lombok.Data;

@Data
public class AlunoDTO {
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String observacao;
	private Date dataCadastro;
	
	public AlunoDTO converter(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
		BeanUtils.copyProperties(aluno, alunoDTO);		
		return alunoDTO;
	}
	
	public Page<AlunoDTO> converterListaAlunoDTO(Page<Aluno> pageAluno){
		Page<AlunoDTO> listaDTO = pageAluno.map(this::converter);
		return listaDTO;
	}
}
