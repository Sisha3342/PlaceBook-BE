package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.filters.AdminUserFilter;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> findByEmail(String email);

    List<User> findUsers(AdminUserFilter adminUserFilter);
}

