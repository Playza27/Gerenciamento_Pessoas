package com.universidade.gerenciamentopessoas.repository;

import com.universidade.gerenciamentopessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeStartingWithAndIdadeGreaterThan(String nome, Integer idade);
}