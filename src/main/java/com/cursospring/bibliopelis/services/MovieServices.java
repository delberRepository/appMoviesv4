package com.cursospring.bibliopelis.services;

import com.cursospring.bibliopelis.dto.PeliculaDTO;
import com.cursospring.bibliopelis.model.Pelicula;
import com.cursospring.bibliopelis.repository.IPeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServices {

    private IPeliculaRepository peliculasRepository;
    private ReviewServices rv;


    public MovieServices(IPeliculaRepository peliculasRepository, ReviewServices rv) {
        this.peliculasRepository = peliculasRepository;
        this.rv = rv;
    }

    //OBTENER TODAS LAS PELICULAS
    public List<Pelicula> getAllPeliculas() {
        //LLAMA A LOS METODOS DE LA INTERFACE
        List<Pelicula> peliculas = this.peliculasRepository.findAll();
        return peliculas;
    }

    // Crear película
    public Pelicula crearPelicula(Pelicula pelicula) {
        return peliculasRepository.save(pelicula);
    }

    // Borrar película por id
    public void borrarPelicula(int id) {
        peliculasRepository.deleteById(id);
    }

    //Obtener peliculas por el Id
    public Pelicula getPeliculaById(int id) {
        return peliculasRepository.findById(id).orElse(null);
    }

    //Extraer el Id del Url
    public String extractYoutubeId(String url) {
        String[] parts = url.split("v=");
        if (parts.length > 1) {
            return parts[1].split("&")[0];
        }
        return url;
    }

    public List<Pelicula> findByTitleAndGenero(String titulo, int idGenero) {
        //segun tenga genero valido como parametro llama a un metodo de la interface u otro.
        if (idGenero == 0) {
            List<Pelicula> peliculas = peliculasRepository.findByTituloContaining(titulo);
            return peliculas;
        } else {
            List<Pelicula> peliculas = peliculasRepository.findByTituloContainingAndGeneroId(titulo, idGenero);
            return peliculas;
        }
    }
    // Transformamos la lista de Pelicula en PeliculaDTO

    public List<PeliculaDTO> getAllPeliculasConMedia() {
        List<Pelicula> peliculas = peliculasRepository.findAll();

        return peliculas.stream().map(p -> {
            Double media = rv.getMediaPelicula(p.getId());
            return new PeliculaDTO(p, media);
        }).collect(Collectors.toList());
    }

    public List<PeliculaDTO> findByTitleAndGenero(String title, Integer generoId) {
        // 1. Buscamos las entidades en la DB

        if (generoId == 0) {
            List<Pelicula> entidades = peliculasRepository.findByTituloContaining(title);

            // 2. Las transformamos a DTOs con su nota media
            return entidades.stream().map(p -> {
                Double media = rv.getMediaPelicula(p.getId());
                return new PeliculaDTO(p, media); // Aquí se crea la magia de 'mediaRatingFormateada'
            }).collect(Collectors.toList());

        }else{
            List<Pelicula> entidades = peliculasRepository.findByTituloContainingAndGeneroId(title, generoId);

                // 2. Las transformamos a DTOs con su nota media
                return entidades.stream().map(p -> {
                    Double media = rv.getMediaPelicula(p.getId());
                    return new PeliculaDTO(p, media); // Aquí se crea la magia de 'mediaRatingFormateada'
                }).collect(Collectors.toList());
            }
        }
    }

