package com.alura.challenge_three_literalura_springboot_one.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
