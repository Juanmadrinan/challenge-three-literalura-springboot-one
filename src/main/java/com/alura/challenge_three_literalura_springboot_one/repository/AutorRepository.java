package com.alura.challenge_three_literalura_springboot_one.repository;

import com.alura.challenge_three_literalura_springboot_one.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
