package com.alura.challenge_three_literalura_springboot_one.repository;

import com.alura.challenge_three_literalura_springboot_one.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
