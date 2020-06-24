package com.cimb.exambackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.exambackend.dao.CategoryRepo;
import com.cimb.exambackend.dao.MovieRepo;
import com.cimb.exambackend.entity.Category;
import com.cimb.exambackend.entity.Movie;

@RestController
@RequestMapping("/movies")
//@CrossOrigin
public class MovieController {
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@GetMapping
	public Iterable<Movie> getMovie() {
		return movieRepo.findAll();
	}
	
	@PostMapping
	public Movie addMovie(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}
	
	@PutMapping
	public Movie updateMovie(@RequestBody Movie movie) {
		Movie findMovie = movieRepo.findById(movie.getId()).get();
		if(findMovie == null )
			throw new RuntimeException("Movie not found");
		return movieRepo.save(movie);
	}
	
	@PostMapping("/{movieId}/categories/{categoryId}")
	public Movie addCategoryToMovie(@PathVariable int movieId, @PathVariable int categoryId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		if(findMovie == null )
			throw new RuntimeException("Movie not found");
		Category findGenre = categoryRepo.findById(categoryId).get();
		if(findGenre == null )
			throw new RuntimeException("Genre not found");
		findMovie.getCategories().add(findGenre);
		return movieRepo.save(findMovie);
	}
	
	@DeleteMapping("/{movieId}")
	public void deleteMovie(@PathVariable int movieId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		if(findMovie == null )
			throw new RuntimeException("Movie not found");
		findMovie.getCategories().forEach(category -> {
			List<Movie> genreMovies = category.getMovies();
			genreMovies.remove(findMovie);
			categoryRepo.save(category);
		});
		findMovie.setCategories(null);
		movieRepo.deleteById(movieId);
	}
	
	@DeleteMapping("/{movieId}/categories/{categoryId}")
	public void deleteCategoryOfMovie(@PathVariable int movieId, @PathVariable int categoryId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		if(findMovie == null )
			throw new RuntimeException("Movie not found");
		Category findGenre = categoryRepo.findById(categoryId).get();
		if(findGenre == null )
			throw new RuntimeException("Genre not found");
		findMovie.getCategories().forEach(category -> {
			if(category.getId() == categoryId) {
				List<Movie> categoryMovies = category.getMovies();
				categoryMovies.remove(findMovie);
				categoryRepo.save(category);
			}
		});
	}
}
