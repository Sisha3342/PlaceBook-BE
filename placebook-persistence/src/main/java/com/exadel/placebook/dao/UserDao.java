package com.exadel.placebook.dao;


import com.exadel.placebook.model.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    Optional<User> findById(Long id);

    User findUserByEmail(String email);

    List<User> findAllByIdBetweenAndNameContainingAndSurnameContaining(Long limit, Long offset, String name, String surname);
}

