package com.exadel.placebook.dao;


import com.exadel.placebook.model.entity.Person;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonDao  {
    Person findById(Long id);
    Person findByEmail(String email);
}

