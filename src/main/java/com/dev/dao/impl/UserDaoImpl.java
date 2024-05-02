package com.dev.dao.impl;

import com.dev.dao.UserDao;
import com.dev.model.UserModel;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @Resource
    private SessionFactory sessionFactory;

    private static final String FROM_TABLE_USER = "FROM UserModel um ";
    private static final String WHERE_USERNAME = "where um.username =: username ";
    private static final String WHERE_NOT_USERNAME = "where um.username <>: username ";
    private static final String WHERE_ID = "where um.id =:id ";

    @Override
    public Optional<UserModel> getUserByUsername(String username) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(FROM_TABLE_USER + WHERE_USERNAME, UserModel.class)
                    .setParameter("username", username)
                    .getResultList().stream().findFirst();
        } catch (Exception e) {
            LOG.error("getUserByUsername: ", e);
            return Optional.empty();
        }
    }

    @Override
    public List<UserModel> getAllUsersExceptCurrentOne(String username) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(FROM_TABLE_USER + WHERE_NOT_USERNAME, UserModel.class)
                    .setParameter("username", username)
                    .getResultList();
        } catch (Exception e) {
            LOG.error("getAllUsers: ", e);
            return null;
        }
    }

    @Override
    public Boolean saveOrUpdate(Object model) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(model);
            return true;
        } catch (Exception e) {
            LOG.error("UserDaoImpl saveOrUpdate: ", e);
            return false;
        }
    }

    @Override
    public Optional<UserModel> getUserById(long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(FROM_TABLE_USER + WHERE_ID, UserModel.class)
                    .setParameter("id", id)
                    .getResultList().stream().findFirst();
        } catch (Exception e) {
            LOG.error("getUserById: ", e);
            return Optional.empty();
        }
    }


}
