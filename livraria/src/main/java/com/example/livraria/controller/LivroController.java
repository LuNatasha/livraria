package com.example.livraria.controller;

import com.example.livraria.model.Livro;
import com.example.livraria.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    // Criar um novo livro
    @PostMapping
    public ResponseEntity<Livro> criarLivro(@Valid @RequestBody Livro livro) {
        Livro novoLivro = livroRepository.save(livro);
        return ResponseEntity.ok(novoLivro);
    }

    // Listar todos os livros
    @GetMapping
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar livro existente
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @Valid @RequestBody Livro livroAtualizado) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livro.setTitulo(livroAtualizado.getTitulo());
                    livro.setAutor(livroAtualizado.getAutor());
                    livro.setPreco(livroAtualizado.getPreco());
                    livro.setEstoque(livroAtualizado.getEstoque());
                    Livro livroSalvo = livroRepository.save(livro);
                    return ResponseEntity.ok(livroSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar livro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livroRepository.delete(livro);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}