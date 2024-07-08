package com.alura.challenge_three_literalura_springboot_one.repository;

import com.alura.challenge_three_literalura_springboot_one.models.Autor;
import com.alura.challenge_three_literalura_springboot_one.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdioma(String idioma);

    List<Autor> findByTitulo(String titulo);

}
