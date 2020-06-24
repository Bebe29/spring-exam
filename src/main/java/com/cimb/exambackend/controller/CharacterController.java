package com.cimb.exambackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.exambackend.dao.CharacterRepo;
import com.cimb.exambackend.entity.Character;

@RestController
@RequestMapping("/characters")
//@CrossOrigin
public class CharacterController {
	@Autowired
	private CharacterRepo characterRepo;
	
	@GetMapping
	public Iterable<Character> getGenre() {
		return characterRepo.findAll();
	}
	
	@PostMapping
	public Character addCharacter(@RequestBody Character character) {
		return characterRepo.save(character);
	}
}
