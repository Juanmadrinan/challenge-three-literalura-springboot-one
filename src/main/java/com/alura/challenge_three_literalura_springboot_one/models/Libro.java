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
    private String nombreAutor;
    private String idioma;
    private Integer descargas;

    public Libro() {

    }

    public Libro(String titulo, String nombreAutor, String idioma ,Integer descargas) {
        this.titulo = titulo;
        this.nombreAutor =  nombreAutor;
        this.idioma = idioma;
        this.descargas = descargas;
    }

    public Libro(DatosLibro datosLibro, String nombreAutor) {
        this.titulo = datosLibro.titulo();
        this.nombreAutor =  nombreAutor;
        this.idioma = datosLibro.idiomas().toString();
        this.descargas = datosLibro.descargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getnombreAutor() {
        return nombreAutor;
    }

    public void setnombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
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
                "nombreAutor/es: " + nombreAutor + "\n" +
                "Idioma/s: " + idioma + "\n" +
                "Total de Descargas: " + descargas + "\n" +
                "-----------------------------------";
    }
}
