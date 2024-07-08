package com.alura.challenge_three_literalura_springboot_one.menu;

import com.alura.challenge_three_literalura_springboot_one.models.*;
import com.alura.challenge_three_literalura_springboot_one.repository.AutorRepository;
import com.alura.challenge_three_literalura_springboot_one.repository.LibroRepository;
import com.alura.challenge_three_literalura_springboot_one.service.ConsumoAPI;
import com.alura.challenge_three_literalura_springboot_one.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.*;
import java.util.stream.Collectors;

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
                Libro theBook = new Libro();
                Autor autor = new Autor();
                try {
                    theBook = new Libro(libro, autor.datosAutor(json).get(0));
                    List<Libro> listaLibros = repository.findAll();
                    Libro finalTheBook = theBook;

                    Autor autor1 = new Autor(autor.datosAutor(json).get(0), Integer.parseInt(autor.datosAutor(json).get(1)), Integer.parseInt(autor.datosAutor(json).get(2)));

                    var busqueda = listaLibros.stream().filter(l -> l.getTitulo().contains(finalTheBook.getTitulo())).count();
                    if(busqueda == 0) {
                        System.out.println(
                                "------- LIBRO ENCONTRADO📘 -------" + "\n" +
                                        "Titulo: " + theBook.getTitulo() + "\n" +
                                        "Autor/es: " + theBook.getnombreAutor() + "\n" +
                                        "Idioma/s: " + theBook.getIdioma() + "\n" +
                                        "Total de Descargas: " + theBook.getDescargas() + "\n" +
                                        "----------------------------------");
                        repository.save(theBook);
                        autorRepository.save(autor1);
                    } else if (busqueda > 0){
                        System.out.println("------------------------------------- \n" +
                                "+ EL LIBRO YA SE ENCUENTRA GUARDADO +\n" +
                                "-------------------------------------");
                    }
                } catch (NumberFormatException | DataIntegrityViolationException e){
                    System.out.println(
                                    "------- LIBRO ENCONTRADO PERO NO GUARDADO📘 -------" + "\n" +
                                    "##-- ERROR POR DATOS DEMACIADO LARGOS O NULOS --## \n" +
                                            "Titulo: " + theBook.getTitulo() + "\n" +
                                            "Autor/es: " + theBook.getnombreAutor() + "\n" +
                                            "Idioma/s: " + theBook.getIdioma() + "\n" +
                                            "Total de Descargas: " + theBook.getDescargas() + "\n" +
                                            "##-- INTENTA CON OTRO TITULO --## \n" +
                                            "----------------------------------");
                    System.out.println(e.getMessage());
                }
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
            if(autores.isEmpty()){
                System.out.println("+ ------------------------------------------------ +");
                System.out.println("+ Ningun autor registrado vivio durante el año " + fecha + " +");
                System.out.println("+ ------------------------------------------------ +");
            }

        } catch (NumberFormatException e) {
            System.out.println("La fecha ingresada no es un número válido.");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void librosIdioma() {
        System.out.println("""
                Escoge el Idioma por sus Abreviados -> XX
                en -> Ingles
                es -> Español
                pt -> Portugues
                """);
        String finalAbreviado = null;
        try {
            var abreviado = scanner.nextLine().toLowerCase();
            finalAbreviado = "[" + abreviado + "]";
            if (finalAbreviado.contains("en") || finalAbreviado.contains("es") || finalAbreviado.contains("pt")) {
                List<Libro> cantidadLibroIdioma = repository.findByIdioma(finalAbreviado);
                cantidadLibroIdioma.stream().filter(libro -> libro.getIdioma() != null).forEach(System.out::println);
                Long cantidad = cantidadLibroIdioma.stream().filter(libro -> libro.getIdioma() != null).count();
                System.out.println("+-----------------------------------------------------------------------------------------+");
                System.out.println("+ " + "La cantidad de libros guardados en la Base de Datos escritos en el idioma " + finalAbreviado + " son: [" + cantidad + "] +");
                System.out.println("+-----------------------------------------------------------------------------------------+");
            } else {
                System.out.println("Por favor, solo digita los abreviados de busqueda disponible 🔎👨‍🏫");
            }
        } catch (InputMismatchException e) {
            System.out.println("Digitaste los caracteres incorrectos");
        }
    }

    public void topLibro() {
        List<Libro> top = repository.findAll();
        List<Libro> listado = top.stream()
                .filter(t -> t.getDescargas() != null)
                .map(t -> new Libro(t.getTitulo(), t.getnombreAutor(), t.getIdioma(), t.getDescargas()))
                .sorted(Comparator.comparing(Libro::getDescargas).reversed())
                .limit(10)
                .collect(Collectors.toList());

        for (int i = 0; i < listado.size(); i++) {
            int count = i;
            count = count + 1;
            System.out.println(count + ". " + listado.get(i));
        }
    }
}
