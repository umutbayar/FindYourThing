package com.FindYourThing.FindYourThingbackend.service;

import com.FindYourThing.FindYourThingbackend.dto.MovieDTO;
import com.FindYourThing.FindYourThingbackend.exception.MovieNotFoundException;
import com.FindYourThing.FindYourThingbackend.model.Category;
import com.FindYourThing.FindYourThingbackend.model.Movie;
import com.FindYourThing.FindYourThingbackend.repository.CategoryRepository;
import com.FindYourThing.FindYourThingbackend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public MovieDTO saveMovie(Movie movie) {
        movieRepository.save(movie);
        ModelMapper modelMapper = new ModelMapper();
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

        List<Category> categoryList = categoryRepository.findCategoriesByTitle(movie.getTitle());
        movieDTO.setCategories(categoryList);

        return movieDTO;
    }

    public List<MovieDTO> getAllMovies() {
        List<Movie> movieList = movieRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<MovieDTO> movieDTOList = Arrays.asList(modelMapper.map(movieList, MovieDTO[].class));

        for (int i = 0 ; i < movieList.size() ; i++) {
            List<Category> categoryList = categoryRepository.findCategoriesByTitle(movieList.get(i).getTitle());
            movieDTOList.get(i).setCategories(categoryList);
        }

        return  movieDTOList;
    }

    public MovieDTO getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException(id));
        ModelMapper modelMapper = new ModelMapper();
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

        List<Category> categoryList = categoryRepository.findCategoriesByTitle(movie.getTitle());
        movieDTO.setCategories(categoryList);

        return movieDTO;
    }

    public MovieDTO updateMovie(Movie newMovie, Long id) {

        Movie movie = movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException(id));

        if (newMovie.getTitle() != null) {
            movie.setTitle(newMovie.getTitle());
        }
        if (newMovie.getSummary() != null) {
            movie.setSummary(newMovie.getSummary());
        }
        if (newMovie.getPoster() != null) {
            movie.setPoster(newMovie.getPoster());
        }
        if (newMovie.getLength() != null) {
            movie.setLength(newMovie.getLength());
        }

        ModelMapper modelMapper = new ModelMapper();
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

        movieRepository.save(movie);

        List<Category> categoryList = categoryRepository.findCategoriesByTitle(movie.getTitle());
        movieDTO.setCategories(categoryList);

        return movieDTO;
    }

    public MovieDTO deleteMovie(Long id) {

        Movie movie = movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException(id));

        movieRepository.delete(movie);

        ModelMapper modelMapper = new ModelMapper();
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

        List<Category> categoryList = categoryRepository.findCategoriesByTitle(movie.getTitle());
        movieDTO.setCategories(categoryList);
        for (int i = 0 ; i < categoryList.size() ; i++) {
            categoryRepository.delete(categoryList.get(i));
        }

        return movieDTO;
    }

}
