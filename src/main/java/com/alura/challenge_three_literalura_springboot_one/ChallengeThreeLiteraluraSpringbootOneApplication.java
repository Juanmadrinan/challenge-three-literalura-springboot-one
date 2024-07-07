package com.alura.challenge_three_literalura_springboot_one;

import com.alura.challenge_three_literalura_springboot_one.menu.Menu;
import com.alura.challenge_three_literalura_springboot_one.repository.AutorRepository;
import com.alura.challenge_three_literalura_springboot_one.repository.LibroRepository;
import com.alura.challenge_three_literalura_springboot_one.service.ConsumoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class ChallengeThreeLiteraluraSpringbootOneApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(ChallengeThreeLiteraluraSpringbootOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(repository, autorRepository);

        Boolean x = true;
        int seleccion = 0;
        while (x) {
            var seccion = """
                    1. Consultar libros 📘🔎
                    2. Listar libros registrados 🧾📝
                    3. Listar autores registrados 🧾🖋
                    4. listar autores vivos en un determinado año ❤👨‍🏫
                    5. Listar libros por idioma 📚🌎
                    6. Salir ⚡
                    """;
            System.out.println(seccion);
            seleccion = 0;

			while (true) {
                try {
                    seleccion = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Respuesta inválida. Por favor, introduce un número");
                    scanner.nextLine();
                }
        	}

        	if (seleccion <= 0 || seleccion > 6) {
            	System.out.println("Vuelve a intentarlo 😡, por favor 😊");
        	} else {
				switch (seleccion) {
					case 1:
						menu.consultarLibro();
						break;
					case 2:
						menu.librosRegistrados();
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						System.out.println("Adios!! 😉");
						x = false;
						break;
				}
			}
		}
    }
}
