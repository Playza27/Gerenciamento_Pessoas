package com.universidade.gerenciamentopessoas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pessoas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private Integer idade;


    public Pessoa(String nome, Integer idade, Long id, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
        this.cpf = cpf;
    }


    public String getNome(){
        return nome;
    }
     public void setNome(String nome){
        this.nome = nome;
     }

    public Integer getIdade(){
        return idade;
    }
    public void setIdade(Integer idade){
        this.idade = idade;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
}
