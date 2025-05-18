package com.universidade.gerenciamentopessoas.controller;

import com.universidade.gerenciamentopessoas.dto.PessoaDTO;
import com.universidade.gerenciamentopessoas.exception.PessoaNotFoundException;
import com.universidade.gerenciamentopessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // POST: Criar uma pessoa
    @PostMapping
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO novaPessoa = pessoaService.criarPessoa(pessoaDTO);
        return ResponseEntity.ok(novaPessoa);
    }

    // GET: Buscar pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPessoaPorId(@PathVariable Long id) {
        PessoaDTO pessoaDTO = pessoaService.buscarPessoaPorId(id);
        return ResponseEntity.ok(pessoaDTO);
    }

    // PUT: Atualizar uma pessoa
    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO pessoaAtualizada = pessoaService.atualizarPessoa(id, pessoaDTO);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    // DELETE: Deletar uma pessoa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    // GET: Buscar pessoas por nome e idade (ex.: Rafael e idade > 18)
    @GetMapping("/buscar")
    public ResponseEntity<List<PessoaDTO>> buscarPessoasPorNomeEIdade(
            @RequestParam String nome,
            @RequestParam Integer idade) {
        List<PessoaDTO> pessoas = pessoaService.buscarPessoasPorNomeEIdade(nome, idade);
        return ResponseEntity.ok(pessoas);
    }

    // Tratamento de exceção para PessoaNotFoundException
    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<String> handlePessoaNotFoundException(PessoaNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}