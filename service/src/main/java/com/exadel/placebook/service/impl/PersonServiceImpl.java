package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.PersonConverter;
import com.exadel.placebook.model.dto.PersonDto;
import com.exadel.placebook.model.entity.Person;
import com.exadel.placebook.dao.PersonDao;
import com.exadel.placebook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonConverter personConverter;

    @Override
    public PersonDto findById(Long id) {
        Person p = personDao.findById(id);
        return p == null ? null : personConverter.convert(p);
    }

    @Override
    public PersonDto findByEmail(String email) {
        Person p = personDao.findByEmail(email);
        return p == null ? null : personConverter.convert(p);
    }
}
