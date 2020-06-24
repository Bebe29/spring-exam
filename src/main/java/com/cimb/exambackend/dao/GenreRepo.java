package com.cimb.exambackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.exambackend.entity.Genre;

public interface GenreRepo extends JpaRepository<Genre, Integer>{

}
