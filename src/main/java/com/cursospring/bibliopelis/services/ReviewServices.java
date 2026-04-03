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


    public Boolean crearReview(Review review) {
        int usuarioId = review.getUsuario().getId();
        int peliculaId = review.getPelicula().getId();

        //aqui compruebo que el usuario no haya escrito ninguna reseña

        if (reviewRepository.existsByUsuarioIdAndPeliculaId(usuarioId, peliculaId)) {
            return false;
        }
        else{

            Usuario usuarioExistente = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Pelicula peliculaExistente = peliculaRepository.findById(peliculaId)
                    .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));

            review.setUsuario(usuarioExistente);
            review.setPelicula(peliculaExistente);
            reviewRepository.save(review);

        }return true;
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
    public boolean yaExisteReview(int usuarioId, int peliculaId) {
        return reviewRepository.existsByUsuarioIdAndPeliculaId(usuarioId, peliculaId);
    }

}