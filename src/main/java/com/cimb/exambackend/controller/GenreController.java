package com.cimb.exambackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.exambackend.dao.GenreRepo;
import com.cimb.exambackend.entity.Genre;

@RestController
@RequestMapping("/genres")
//@CrossOrigin
public class GenreController {
	@Autowired
	private GenreRepo genreRepo;
	
	@GetMapping
	public Iterable<Genre> getGenre() {
		return genreRepo.findAll();
	}
	
	@PostMapping
	public Genre addGenre(@RequestBody Genre genre) {
		return genreRepo.save(genre);
	}
	
}
