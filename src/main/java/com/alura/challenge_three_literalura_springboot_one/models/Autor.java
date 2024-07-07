package com.alura.challenge_three_literalura_springboot_one.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @JsonAlias("name") private String nombre;
    @JsonAlias("birth_year") private Integer fechaNacimiento;
    @JsonAlias("death_year") private Integer fechaFallecimiento;

    public Autor(){

    }

    public Autor(String nombre, Integer fechaNacimiento, Integer fechaFallecimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Autor(String datosAutor) {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    @Override
    public String toString() {
        return "{ " + "AUTOR/A👨‍🏫 -> " +
                "Nombre: " + nombre +
                ", Fecha de Nacimiento 🐣: " + fechaNacimiento +
                ", Fecha de Fallecimiento ⭐: " + fechaFallecimiento +
                "}";
    }

    public List<String> datosAutor(String json) {
        List<String> listaAutores = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode arrayExternoNode = rootNode.path("results");
            if (arrayExternoNode.isArray()) {
                JsonNode arrayInternoNode = arrayExternoNode.get(0).path("authors");
                if (arrayInternoNode != null && !arrayInternoNode.isEmpty()) {
                    for (JsonNode autorNode : arrayInternoNode) {
                        var datos = objectMapper.treeToValue(autorNode, Autor.class);
                        listaAutores.add(datos.getNombre());
                        listaAutores.add(String.valueOf(datos.getFechaNacimiento()));
                        listaAutores.add(String.valueOf(datos.getFechaFallecimiento()));
                    }
                } else {
                    System.out.println("El array interno no paso");
                }
            } else {
                System.out.println("El array externo no paso");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaAutores;
    }
}
