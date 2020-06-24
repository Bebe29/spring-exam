package com.cimb.exambackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cimb.exambackend.entity.Character;

public interface CharacterRepo extends JpaRepository<Character, Integer> {

}
