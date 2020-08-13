package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.sorting.AdminUserFilter;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> findByEmail(String email);

    List<User> findUsers(AdminUserFilter adminUserFilter, Long id);

    List<User> findUsers(String text);
}

