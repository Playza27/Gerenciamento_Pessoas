package com.universidade.gerenciamentopessoas.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;
}