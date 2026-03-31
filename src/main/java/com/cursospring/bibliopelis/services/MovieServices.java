package com.cursospring.bibliopelis.services;

import com.cursospring.bibliopelis.model.Pelicula;
import com.cursospring.bibliopelis.repository.IPeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServices {

    private IPeliculaRepository peliculasRepository;


    public MovieServices(IPeliculaRepository peliculasRepository) {
        this.peliculasRepository = peliculasRepository;
    }

    //OBTENER TODAS LAS PELICULAS
    public List<Pelicula> getAllPeliculas(){
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

    public List<Pelicula>findByTitleAndGenero(String titulo, int idGenero){
        //segun tenga genero valido como parametro llama a un metodo de la interface u otro.
      if (idGenero==0)  {
        List<Pelicula>peliculas= peliculasRepository.findByTituloContaining(titulo);
        return peliculas;
      }else{
          List<Pelicula>peliculas=peliculasRepository.findByTituloContainingAndGeneroId(titulo,idGenero);
        return  peliculas;
      }
    }
}
