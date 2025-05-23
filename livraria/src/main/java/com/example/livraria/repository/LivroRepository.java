package com.example.livraria.repository;

import com.example.livraria.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Aqui você pode colocar métodos personalizados, como:
    // List<Livro> findByAutor(String autor);
}
