package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.filters.AdminUserFilter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public Optional<User> findById(Long id) {
        Session session = getSession();
        return Optional.ofNullable(session
                .createQuery("from User p where p.id = :id", User.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Session session = getSession();
        return Optional.ofNullable(session
                .createQuery("from User p where p.email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult());
    }

    @Override
    public List<User> findUsers(AdminUserFilter adminUserFilter) {
        Session session = getSession();
        Query<User> query = session
                .createQuery("from User p where p.name LIKE CONCAT('%', :name, '%') and p.surname LIKE CONCAT('%', :surname, '%')", User.class)
                .setParameter("name", adminUserFilter.getName())
                .setParameter("surname", adminUserFilter.getSurname())
                .setMaxResults(adminUserFilter.getLimit())
                .setFirstResult(adminUserFilter.getOffset());
        return query.list();
    }
}
