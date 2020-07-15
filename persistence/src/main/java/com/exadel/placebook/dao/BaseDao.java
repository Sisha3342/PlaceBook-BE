package com.exadel.placebook.dao;

import org.hibernate.Session;

/**
 * TODO
 *
 * @author Anton Harakh
 * @since 7/9/2019
 */
public interface BaseDao {


    /**
     * Get hibernate session.
     *
     * @return the hibernate session.
     */
    Session getSession();

}
