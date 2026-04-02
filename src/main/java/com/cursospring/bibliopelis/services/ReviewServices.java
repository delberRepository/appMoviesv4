package com.cursospring.bibliopelis.services;

import com.cursospring.bibliopelis.model.Pelicula;
import com.cursospring.bibliopelis.model.Review;
import com.cursospring.bibliopelis.model.Usuario;
import com.cursospring.bibliopelis.repository.IPeliculaRepository;
import com.cursospring.bibliopelis.repository.IReviewRepository;
import com.cursospring.bibliopelis.repository.IUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReviewServices {

    private final IReviewRepository reviewRepository;
    private final IPeliculaRepository peliculaRepository;
    private final IUsuarioRepository usuarioRepository;


    public Review crearReview(Review review) {
        // Recuperamos las entidades existentes
        Usuario usuarioExistente = usuarioRepository.findById(review.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Pelicula peliculaExistente = peliculaRepository.findById(review.getPelicula().getId())
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
        review.setUsuario(usuarioExistente);
        review.setPelicula(peliculaExistente);
        return reviewRepository.save(review);
        //este es un metodo del repositorio que pertece a la superclase
    }

    public List<Review> getReviewsByPelicula(Integer pelicula_id) {
        return reviewRepository.findByPeliculaId(pelicula_id);
    }

    public Double getMediaPelicula(int peliculaId) {
        return reviewRepository.getMediaByPeliculaId(peliculaId);
    }
    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }
}