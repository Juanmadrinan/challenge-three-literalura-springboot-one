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
import java.util.Locale;
import java.util.Objects;
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
        List<Libro> libros = repository.findAll();
        libros.stream().forEach(System.out::println);
    }

    public void autoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.stream().forEach(System.out::println);
    }

    public void autoresFecha() {
        List<Autor> autores = autorRepository.findAll();
        System.out.println("Escribe la fecha: ");
        try {
            var fecha = scanner.nextInt();
            scanner.nextLine();
            autores.stream()
                    .filter(a -> a.getFechaNacimiento() != null && a.getFechaFallecimiento() != null)
                    .filter(a -> a.getFechaNacimiento() <= fecha && a.getFechaFallecimiento() >= fecha)
                    .forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("La fecha ingresada no es un número válido.");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void librosIdioma() {
        List<Libro> listarLibroIdioma = repository.findAll();
        System.out.println("""
                Escoge el Idioma por sus Abreviados -> XX
                en -> Ingles
                es -> Español
                pt -> Portugues
                """);
        var abreviado = scanner.nextLine().toLowerCase();
        String finalAbreviado = "[" + abreviado + "]";
        listarLibroIdioma.stream()
                .filter(libro -> libro.getIdioma() != null)
                .filter(libro -> Objects.equals(libro.getIdioma(), finalAbreviado))
                .forEach(System.out::println);
    }
}
