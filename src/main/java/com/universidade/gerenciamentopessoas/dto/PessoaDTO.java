package com.universidade.gerenciamentopessoas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;
}
