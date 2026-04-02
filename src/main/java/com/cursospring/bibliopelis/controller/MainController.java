package com.cursospring.bibliopelis.controller;

import com.cursospring.bibliopelis.dto.PeliculaDTO;
import com.cursospring.bibliopelis.model.Genero;
import com.cursospring.bibliopelis.model.Pelicula;
import com.cursospring.bibliopelis.model.Review;
import com.cursospring.bibliopelis.model.Usuario;
import com.cursospring.bibliopelis.services.GenereServices;
import com.cursospring.bibliopelis.services.MovieServices;
import com.cursospring.bibliopelis.services.ReviewServices;
import com.cursospring.bibliopelis.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class MainController {
    private GenereServices gs;
    private MovieServices ms;
    private ReviewServices rs;
    private UserServices us;


    //aqui le inyecto el genereServices, es decir se inyectan servicios y repositorios

    @GetMapping("/")
    public String getMainPage(Model model) {
        // 1. Cargamos los datos básicos
        List<Genero> generosList = gs.getAllGeneros();
        List<PeliculaDTO> peliculaList = ms.getAllPeliculasConMedia();
        // 3. Pasamos todo al modelo
        model.addAttribute("generos", generosList);
        model.addAttribute("peliculas", peliculaList);


        return "index";
    }

    @GetMapping("/createMovie")
    public String createMovie(Model model) {
        model.addAttribute("pelicula", new Pelicula());

        //OBTENER PELIS DEL SERVICIO y LOS AÑADE A OBJETO MODELO
        List<Genero> generosList = gs.getAllGeneros();
        model.addAttribute("generos", generosList);
        return "nuevaPelicula";
    }

    @PostMapping("/createMovie")
    public String createMovie(@ModelAttribute Pelicula pelicula){
        this.ms.crearPelicula(pelicula);
        return "redirect:/";
    }
    @GetMapping("/verFicha/{id}")
    public String verFicha(@PathVariable int id, Model model) {

        Pelicula pelicula = ms.getPeliculaById(id);
        //aqui llamo al metodo para extraer el identificador y
        //luego vuelvo a cagar en la variable videoUrl.
        String videoId = ms.extractYoutubeId(pelicula.getVideoUrl());
        pelicula.setVideoUrl(videoId);
        // 🔥 AÑADIR REVIEWS
        List<Review> reviews = rs.getReviewsByPelicula(id);

        //AQUI AÑADO LA MEDIA DE LAS PELIS
        Double media = rs.getMediaPelicula(id);
        model.addAttribute("mediaRating", media);

        model.addAttribute("pelicula", pelicula);
        model.addAttribute("reviews", reviews);
        return "fichaBorrar";
    }
    @PostMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable int id){
        this.ms.borrarPelicula(id);
        return "redirect:/";

    }
   @PostMapping("/searchMovie")
    public String searchMovie(@RequestParam String searchTitle, @RequestParam Integer searchGenero,Model model){
       List<Genero> generosList = gs.getAllGeneros();
       List<PeliculaDTO> peliculaList =ms.findByTitleAndGenero(searchTitle,searchGenero);
       model.addAttribute("generos",generosList);
       model.addAttribute("peliculas", peliculaList);
       return"index";
    }
    @PostMapping("/addReview")
    public String addReview(@RequestParam String contenido,
                            @RequestParam int rating,
                            @RequestParam int peliculaId)
                            {

        Review review = new Review();
        review.setContenido(contenido);
        review.setRating(rating);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogueado = (Usuario) auth.getPrincipal();

        Usuario usuario = new Usuario();
        usuario.setId(usuarioLogueado.getId());

        Pelicula pelicula = new Pelicula();
        pelicula.setId(peliculaId);

        review.setUsuario(usuario);
        review.setPelicula(pelicula);

        rs.crearReview(review);

        return "redirect:/verFicha/" + peliculaId;
    }
//LOGIN
    @GetMapping("/login")
    public String login() {

        return "login";
    }
 //CREAR USUARIO
    @GetMapping("/register")

        public String crearUsuario(Model model) {
            model.addAttribute("usuario", new Usuario());
            return "crearCuenta";
        }
    @PostMapping("/register")
    public String crearUsuario(@ModelAttribute Usuario usuario){
        this.us.crearUsuario(usuario);
        return "redirect:/";
    }

    }



