package com.cursospring.bibliopelis.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@Getter
@Setter
@Entity
@Table(name = "peliculas") // el nombre exacto de la tabla en la base
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="year")
    private Integer anyo;
    private String titulo;
    private String director;
    private String imgUrl;
    private String videoUrl;
    @Column(columnDefinition = "text")
    private String descripcion;
    @ManyToOne
    @JsonIgnore // <- Esto detiene la recursión
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    private List<Review> reviews;


    public Pelicula() {
    }

    public Pelicula(Integer anyo,String titulo, String director, String imgUrl, String videoUrl, Genero genero) {
        this.titulo = titulo;
        this.director = director;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.genero = genero;
        this.reviews = reviews;
    }

    public Pelicula(int id, Integer anyo, String titulo, String director, String imgUrl, String videoUrl, Genero genero, List<Review> reviews) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.genero = genero;
        this.reviews = reviews;
    }


}
