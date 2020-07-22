package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.enums.Role;
import com.exadel.placebook.model.filters.AdminUserFilter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public Optional<User> findByEmail(String email) {
        Session session = getSession();
        return session
                .createQuery("from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .uniqueResultOptional();
    }

    @Override
    public List<User> findUsers(AdminUserFilter adminUserFilter) {
        Session session = getSession();
        Query<User> query = session
                .createQuery("from User u where concat_ws(' ', u.name, u.surname) like :text", User.class)
                .setParameter("text", StringUtils.wrap(adminUserFilter.getText(), "%"))
                .setMaxResults(adminUserFilter.getLimit())
                .setFirstResult(adminUserFilter.getOffset());
        return query.list();
    }

    @Override
    public int update(Long userId, Role role) {
        Session session = getSession();
        Query query = session.createQuery("update User u set u.role = :role " +
                "where u.id = :userId")
                .setParameter("userId", userId)
                .setParameter("role", role);
        return query.executeUpdate();
    }
}
