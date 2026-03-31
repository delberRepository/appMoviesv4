package com.cursospring.bibliopelis.controller;

import com.cursospring.bibliopelis.model.Review;
import com.cursospring.bibliopelis.services.ReviewServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//APIREST
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewServices reviewService;

    public ReviewController(ReviewServices reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review crearReview(@RequestBody Review review) {
        return reviewService.crearReview(review);
    }

    @GetMapping("/pelicula/{id}")
    public List<Review> getReviews(@PathVariable Integer id) {
        return reviewService.getReviewsByPelicula(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}