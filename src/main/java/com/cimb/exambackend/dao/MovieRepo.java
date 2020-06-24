package com.cimb.exambackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.exambackend.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer>{

}
