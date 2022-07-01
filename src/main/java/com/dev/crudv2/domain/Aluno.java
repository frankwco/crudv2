package com.dev.crudv2.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
 
@Entity
@Table(name = "aluno")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Aluno implements Serializable {
 
    private static final long serialVersionUID = 4048798961366546485L;
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Schema(description = "Nome do aluno",example = "José da Silva",required = true)
    private String nome;
    
    @Schema(description = "CPF do Aluno",example = "123.522.365-52",required = false)
    private String cpf;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
    @Column(length = 4000)
    private String observacao;  
    
    @ManyToOne
    @JoinColumn(name = "idCidade")
    private Cidade cidade;
    
    private String senha;    
    
    
}
