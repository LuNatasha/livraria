package com.example.livraria;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final Map<Long, Livro> livros = new HashMap<>();

    @PostMapping
    public ResponseEntity<?> criarLivro(@RequestBody @Valid Livro livro) {
        livros.put(livro.getId(), livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok(new ArrayList<>(livros.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Livro livro = livros.get(id);
        if (livro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLivro(@PathVariable Long id, @RequestBody @Valid Livro dadosAtualizados) {
        Livro livroExistente = livros.get(id);
        if (livroExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        livroExistente.setTitulo(dadosAtualizados.getTitulo());
        livroExistente.setAutor(dadosAtualizados.getAutor());
        livroExistente.setPreco(dadosAtualizados.getPreco());
        livroExistente.setEstoque(dadosAtualizados.getEstoque());

        return ResponseEntity.ok(livroExistente);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable Long id) {
        Livro removido = livros.remove(id);
        if (removido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }
        return ResponseEntity.ok("Livro removido com sucesso");
    }
}