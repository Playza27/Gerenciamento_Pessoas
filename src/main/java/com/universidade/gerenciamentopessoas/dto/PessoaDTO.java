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

    public String getNome() {
    return nome;
    }

    public Integer getIdade() {
    return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
    }

    public void setNome(String nome) {
    }

    public void setIdade(Integer idade) {
    }

    public void setCpf(String cpf) {
    }

    public Long setId() {
    return id;
    }
}