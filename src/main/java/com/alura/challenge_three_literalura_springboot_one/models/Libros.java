package com.alura.challenge_three_literalura_springboot_one.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Libros(
        @JsonAlias("results") List<DatosLibro> libros)
{

}
