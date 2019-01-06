package com.nokinobi.repository;

import com.nokinobi.items.IPhone;
import com.nokinobi.items.User;
import com.nokinobi.repository.exceptions.RepositoryException;
import com.nokinobi.repository.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.*;
import javax.sql.DataSource;


@Repository
public class UserRepository {

    private static final String FindUser = "select u from User u where u.login=:login";
    private static final String GetIdUser = "select u.id from User u where u.login=:login";


    @PersistenceContext
    private EntityManager entityManager;


    public void add(User user) throws RepositoryException {
        try {
            entityManager.persist(user);
            entityManager.flush();
        }catch (PersistenceException e) {
            throw new UserExistException();
        }
    }

    public User find(User user) {
        try {
            return entityManager.createQuery(FindUser, User.class).setParameter("login", user.getLogin()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public int getId(User user) {
        return entityManager.createQuery(GetIdUser, Integer.class).setParameter("login", user.getLogin()).getFirstResult();
    }
}
