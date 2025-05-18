package com.universidade.gerenciamentopessoas.service;

import com.universidade.gerenciamentopessoas.dto.PessoaDTO;
import com.universidade.gerenciamentopessoas.model.Pessoa;
import com.universidade.gerenciamentopessoas.exception.PessoaNotFoundException;
import com.universidade.gerenciamentopessoas.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    // Criar uma pessoa
    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO) {
        logger.info("Criando pessoa com nome: {}", pessoaDTO.getNome());
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setIdade(pessoaDTO.getIdade());

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        logger.info("Pessoa criada com sucesso, ID: {}", pessoaSalva.getId());

        pessoaDTO.setId(pessoaSalva.getId());
        return pessoaDTO;
    }

    // Buscar pessoa por ID
    public PessoaDTO buscarPessoaPorId(Long id) {
        logger.info("Buscando pessoa com ID: {}", id);
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Pessoa com ID {} não encontrada", id);
                    return new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada");
                });

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setCpf(pessoa.getCpf());
        pessoaDTO.setIdade(pessoa.getIdade());
        logger.info("Pessoa encontrada: {}", pessoa.getNome());
        return pessoaDTO;
    }

    // Atualizar uma pessoa (PUT)
    public PessoaDTO atualizarPessoa(Long id, PessoaDTO pessoaDTO) {
        logger.info("Atualizando pessoa com ID: {}", id);
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Pessoa com ID {} não encontrada para atualização", id);
                    return new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada");
                });

        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setIdade(pessoaDTO.getIdade());

        Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);
        logger.info("Pessoa atualizada com sucesso, ID: {}", pessoaAtualizada.getId());

        pessoaDTO.setId(pessoaAtualizada.getId());
        return pessoaDTO;
    }

    // Deletar uma pessoa
    public void deletarPessoa(Long id) {
        logger.info("Deletando pessoa com ID: {}", id);
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Pessoa com ID {} não encontrada para deleção", id);
                    return new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada");
                });

        pessoaRepository.delete(pessoa);
        logger.info("Pessoa deletada com sucesso, ID: {}", id);
    }

    // Buscar pessoas com nome "Rafael" e idade maior que 18
    public List<PessoaDTO> buscarPessoasPorNomeEIdade(String nome, Integer idade) {
        logger.info("Buscando pessoas com nome {} e idade maior que {}", nome, idade);
        List<Pessoa> pessoas = pessoaRepository.findByNomeStartingWithAndIdadeGreaterThan(nome, idade);
        List<PessoaDTO> pessoaDTOs = pessoas.stream().map(pessoa -> {
            PessoaDTO dto = new PessoaDTO();
            dto.setId(pessoa.getId());
            dto.setNome(pessoa.getNome());
            dto.setCpf(pessoa.getCpf());
            dto.setIdade(pessoa.getIdade());
            return dto;
        }).collect(Collectors.toList());
        logger.info("Encontradas {} pessoas com nome {} e idade maior que {}", pessoaDTOs.size(), nome, idade);
        return pessoaDTOs;
    }
}
