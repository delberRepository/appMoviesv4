package com.cursospring.bibliopelis.model;
import com.cursospring.bibliopelis.model.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String director;
    private String imgUrl;
    private String videoUrl;
    @Column(columnDefinition = "text")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    public Peliculas() {
    }

    public Peliculas(String titulo, String director, String imgUrl, String videoUrl, Genero genero) {
        this.titulo = titulo;
        this.director = director;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.genero = genero;
    }

    public Peliculas(int id, String titulo, String director, String imgUrl, String videoUrl, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Peliculas{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", genero=" + genero +
                '}';
    }
}
