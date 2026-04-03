package com.cursospring.bibliopelis.repository;

import com.cursospring.bibliopelis.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review,Integer> {
    @Query("SELECT r FROM Review r JOIN FETCH r.usuario WHERE r.pelicula.id = :peliculaId")
    List<Review> findByPeliculaId(@Param("peliculaId") Integer peliculaId);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.pelicula.id = :peliculaId")
    Double getMediaByPeliculaId(@Param("peliculaId") int peliculaId);
    boolean existsByUsuarioIdAndPeliculaId(int usuarioId, int peliculaId);
}
