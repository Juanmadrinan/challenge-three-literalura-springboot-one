package com.alura.challenge_three_literalura_springboot_one.menu;

import com.alura.challenge_three_literalura_springboot_one.models.*;
import com.alura.challenge_three_literalura_springboot_one.repository.AutorRepository;
import com.alura.challenge_three_literalura_springboot_one.repository.LibroRepository;
import com.alura.challenge_three_literalura_springboot_one.service.ConsumoAPI;
import com.alura.challenge_three_literalura_springboot_one.service.ConvierteDatos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final String URL_BASE = "https://gutendex.com/books";
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI peticion = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository repository;
    private AutorRepository autorRepository;

    public Menu(LibroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public void consultarLibro() {
        System.out.println("Consulta el libro, por el titulo 📘🔎");

        var texto = scanner.nextLine();
        var json = peticion.peticion(URL_BASE + "?search=" + texto.replace(" ", "%20"));

        if (json != null && !json.isEmpty()) {

            var conversion = convierteDatos.obtenerDatos(json, Libros.class);

            if (conversion.libros().size() > 0) {
                DatosLibro libro = conversion.libros().get(0);
                Autor autor = new Autor();
                Libro theBook = new Libro(libro, autor.datosAutor(json).get(0));
                Autor autor1 = new Autor(autor.datosAutor(json).get(0), Integer.parseInt(autor.datosAutor(json).get(1)), Integer.parseInt(autor.datosAutor(json).get(2)));

                repository.save(theBook);
                autorRepository.save(autor1);
                System.out.println("probando autor1:" + autor1);

                System.out.println(
                        "------- LIBRO ENCONTRADO📘 -------" + "\n" +
                                "Titulo: " + theBook.getTitulo() + "\n" +
                                "Autor/es: " + theBook.getAutor() + "\n" +
                                "Idioma/s: " + theBook.getIdioma() + "\n" +
                                "Total de Descargas: " + theBook.getDescargas() + "\n" +
                        "----------------------------------");
            } else {
                System.out.println(
                        "\n+ ----------------------------------------------- +\n" +
                        "+ No se encontro ningún libro con ese titulo 📕❌ +\n" +
                        "+ ----------------------------------------------- +\n");
            }

        } else {
            System.out.println("No data received from the API.");
        }
    }

    public void librosRegistrados() {

    }

}
