package com.rentalkars.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.User;
import com.rentalkars.hibernate.utils.HibernateConfig;
import org.hibernate.query.Query;

public class UserDao {

    public void saveUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            tx = session.beginTransaction();
            // save the User object
            session.save(user);
            // commit transaction
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    /* Query da creare:
    * seleziona per email
    * seleziona per email e password
    * seleziona per nome o iniziale
    * seleziona per cognome o iniziale
     */

    public User selEmail(String email) {
        Transaction tx = null;
        User user = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select * from user where email = :email");
            query.setParameter("email", email);

            user = (User) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

}
