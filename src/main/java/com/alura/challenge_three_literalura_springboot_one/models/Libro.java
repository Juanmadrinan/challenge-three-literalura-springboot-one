package com.alura.challenge_three_literalura_springboot_one.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idioma;
    private Integer descargas;

    public Libro() {

    }

    public Libro(DatosLibro datosLibro, String autor) {
        this.titulo = datosLibro.titulo();
        this.autor =  autor;
        this.idioma = datosLibro.idiomas().toString();
        this.descargas = datosLibro.descargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return  "------------- LIBRO 📗 ------------" + "\n" +
                "Titulo: " + titulo + "\n" +
                "Autor/es: " + autor + "\n" +
                "Idioma/s: " + idioma + "\n" +
                "Total de Descargas: " + descargas + "\n" +
                "-----------------------------------";
    }
}
