package com.alura.challenge_three_literalura_springboot_one.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String peticion(String url) {
        HttpClient client = HttpClient.newBuilder()
                //Codigo agregado por causa del error 301 -> Redireccionaba mi peticion.
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al enviar la petición: " + e.getMessage());
            return null;
        }
    // Condicional para encontrar el error de respuesta vacia
    if (response.statusCode() != 200) {
        System.out.println("Error al recibir la respuesta del servidor: " + response.statusCode());
        return null;
    }

        String json = response.body();
        return json;
    }
}
