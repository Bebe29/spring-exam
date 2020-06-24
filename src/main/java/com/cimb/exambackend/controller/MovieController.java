package com.cimb.exambackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.exambackend.dao.GenreRepo;
import com.cimb.exambackend.dao.MovieRepo;
import com.cimb.exambackend.entity.Genre;
import com.cimb.exambackend.entity.Movie;

@RestController
@RequestMapping("/movies")
//@CrossOrigin
public class MovieController {
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private GenreRepo genreRepo;
	
	@GetMapping
	public Iterable<Movie> getMovie() {
		return movieRepo.findAll();
	}
	
	@PostMapping
	public Movie addMovie(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}
	
	@PostMapping("/{movieId}/genres/{genreId}")
	public Movie addGenreToMovie(@PathVariable int movieId, @PathVariable int genreId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		if(findMovie == null )
			throw new RuntimeException("Movie not found");
		Genre findGenre = genreRepo.findById(genreId).get();
		if(findGenre == null )
			throw new RuntimeException("Genre not found");
		findMovie.getGenres().add(findGenre);
		return movieRepo.save(findMovie);
	}
	
	@PostMapping("/{movieId}/genres/{genreId}/newgenres/{newgenreId}")
	public Movie updateGenreToMovie(@PathVariable int movieId, @PathVariable int genreId, @PathVariable int newgenreId) {
		
		return movieRepo.save(findMovie);
	}
}
