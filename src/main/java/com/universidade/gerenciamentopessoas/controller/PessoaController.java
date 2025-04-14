package com.universidade.gerenciamentopessoas.controller;

import com.universidade.gerenciamentopessoas.dto.PessoaDTO;
import com.universidade.gerenciamentopessoas.model.Pessoa;
import com.universidade.gerenciamentopessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;


    // POST: Criar uma pessoa
    @PostMapping
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa(pessoaDTO.getNome(), pessoaDTO.getIdade(), pessoaDTO.setId(), pessoaDTO.getCpf());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setIdade(pessoaDTO.getIdade());

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        PessoaDTO responseDTO = new PessoaDTO();
        responseDTO.setId(pessoaSalva.getId());
        responseDTO.setNome(pessoaSalva.getNome());
        responseDTO.setCpf(pessoaSalva.getCpf());
        responseDTO.setIdade(pessoaSalva.getIdade());

        return ResponseEntity.ok(responseDTO);
    }

    // GET: Buscar uma pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPessoaPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pessoa pessoa = pessoaOptional.get();
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setCpf(pessoa.getCpf());
        pessoaDTO.setIdade(pessoa.getIdade());

        return ResponseEntity.ok(pessoaDTO);
    }
}