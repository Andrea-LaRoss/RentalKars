package com.rentalkars.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.User;
import com.rentalkars.hibernate.utils.HibernateConfig;

public class UserDao {
    public void saveUser(User User) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the User object
            session.save(User);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List < User > getUsers() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }
}
