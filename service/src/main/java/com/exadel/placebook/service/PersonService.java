package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.PersonDto;

public interface PersonService {

    PersonDto findById(Long id);

    PersonDto findByEmail(String email);
}
