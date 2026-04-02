package com.cursospring.bibliopelis.dto;

import com.cursospring.bibliopelis.model.Pelicula;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//He creado este DTO para la vista de la ficha peliculas en la pagina index.html
public class PeliculaDTO {
    private Integer id;
    private String titulo;
    private String imgUrl;
    private Double media;
    private String mediaRatingFormateada; // Cambiamos Double por String

    // Constructor, Getters y Setters
    public PeliculaDTO(Pelicula p, Double media) {
        this.id = p.getId();
        this.titulo = p.getTitulo();
        this.imgUrl = p.getImgUrl();
        this.media = media;

        if (media == null || media == 0) {
            this.mediaRatingFormateada = " ";
        } else {
            // Si el resto de dividir por 1 es 0, es un entero (ej: 5.0 -> 5)
            this.mediaRatingFormateada = (media % 1 == 0)
                    ? String.valueOf(media.intValue())
                    : String.valueOf(media);
        }
    }
}