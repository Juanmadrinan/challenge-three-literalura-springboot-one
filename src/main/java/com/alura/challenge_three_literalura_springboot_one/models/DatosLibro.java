package com.alura.challenge_three_literalura_springboot_one.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(

    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<Autor> autores,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("download_count") Integer descargas) {


}
