package com.FindYourThing.FindYourThingbackend.controller;

import com.FindYourThing.FindYourThingbackend.dto.MovieDTO;
import com.FindYourThing.FindYourThingbackend.model.Movie;
import com.FindYourThing.FindYourThingbackend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @PostMapping("/savemovie")
    public MovieDTO saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/getallmovies")
    public List<MovieDTO> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/getmovie/{id}")
    public MovieDTO getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @PutMapping("/updatemovie/{id}")
    public MovieDTO updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        return movieService.updateMovie(movie, id);
    }

    @DeleteMapping("/deletemovie/{id}")
    public MovieDTO deleteMovie(@PathVariable Long id) {
        return movieService.deleteMovie(id);
    }

}
