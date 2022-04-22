package com.rentalkars.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.Rent;
import com.rentalkars.hibernate.utils.HibernateConfig;

public class RentDao {
    public void saveRent(Rent Rent) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Rent object
            session.save(Rent);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List < Rent > getRents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Rent", Rent.class).list();
        }
    }
}
