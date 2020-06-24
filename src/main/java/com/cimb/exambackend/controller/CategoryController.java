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
@RequestMapping("/categories")
//@CrossOrigin
public class CategoryController {
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private MovieRepo movieRepo;
	
	@GetMapping
	public Iterable<Category> getCategory() {
		return categoryRepo.findAll();
	}
	
	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		return categoryRepo.save(category);
	}
	
	@PutMapping
	public Category updateCategory(@RequestBody Category category) {
		Category findGenre = categoryRepo.findById(category.getId()).get();
		if(findGenre == null )
			throw new RuntimeException("Genre not found");
		return categoryRepo.save(category);
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable int categoryId) {
		Category findGenre = categoryRepo.findById(categoryId).get();
		if(findGenre == null )
			throw new RuntimeException("Genre not found");
		findGenre.getMovies().forEach(movie -> {
			List<Category> movieGenres = movie.getCategories();
			movieGenres.remove(findGenre);
			movieRepo.save(movie);
		});
		findGenre.setMovies(null);
		categoryRepo.deleteById(categoryId);
	}
	
	@GetMapping("/{categoryId}/movies")
	public List<Movie> getMovieOfCategory(@PathVariable int categoryId) {
		Category findGenre = categoryRepo.findById(categoryId).get();
		return findGenre.getMovies();
	}
	
}
