package com.exadel.placebook.dao;


import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.filters.AdminUserFilter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findUsers(AdminUserFilter adminUserFilter);
}

