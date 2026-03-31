package com.cursospring.bibliopelis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Genero{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @OneToMany(mappedBy = "genero")
    @JsonManagedReference
    private List<Pelicula> peliculas;

    public Genero() {}

    public Genero( int id, String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public Genero(String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public Genero(int id, String nombre, List<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.peliculas = peliculas;
    }



    public int getId() {

        return id;
    }
    public String getNombre() {

        return nombre;
    }
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public List<Pelicula> getPeliculas() {

        return peliculas;
    }
    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}