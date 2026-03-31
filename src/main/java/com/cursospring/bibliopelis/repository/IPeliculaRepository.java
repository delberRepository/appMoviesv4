package com.cursospring.bibliopelis.repository;
import com.cursospring.bibliopelis.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPeliculaRepository extends JpaRepository<Pelicula,Integer> {

    List<Pelicula> findByTituloContaining(String titulo);

    //aqui tengo que pasarle el tipo de consulta a traves del nombre de la funcion, es importante que este bien formulado para que lo
    //coja, tiene que coincidir con el nombre de la identidad en la clase.
    List<Pelicula> findByTituloContainingAndGeneroId(String titulo, int genero_id);
}
