package com.cimb.exambackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.exambackend.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
