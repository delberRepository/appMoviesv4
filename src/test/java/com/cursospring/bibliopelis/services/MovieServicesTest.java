package com.cursospring.bibliopelis.services;

import com.cursospring.bibliopelis.model.Genero;
import com.cursospring.bibliopelis.model.Pelicula;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



    @SpringBootTest
    class MovieServicesTest {
        @Autowired
        MovieServices ms;

        @Test
        public void createMovie(){
            ms.crearPelicula(new Pelicula("Critters","Stephen Herek","https://m.media-amazon.com/images/M/MV5BYmFmZTA5ZDMtN2I1Zi00ZGFmLWEyMjMtYjFkZDBjMTYyNmIxXkEyXkFqcGc@._V1_.jpg","https://youtu.be/9V3YGz-u2Ts?si=81eUq_FmC5GbVcQ1",new Genero(1,"Terror")));
        }



        public void findAll(){
            List<Pelicula> movies = ms.getAllPeliculas();
            movies.forEach(movie -> {
                System.out.println(movie);
            });

        }

}