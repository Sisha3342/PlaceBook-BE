package com.exadel.placebook.converter;


import com.exadel.placebook.model.dto.PersonDto;
import com.exadel.placebook.model.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    public PersonDto convert(Person p) {
        PersonDto pd = new PersonDto();
        pd.setId(p.getId());
        pd.setName(p.getName());
        pd.setSurname(p.getSurname());
        return pd;
    }

    public Person convert(PersonDto pd) {
        Person p = new Person();
        p.setName(pd.getName());
        p.setSurname(pd.getSurname());
        p.setId(pd.getId());
        return p;
    }
}
