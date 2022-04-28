package com.rentalkars.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.Rent;
import com.rentalkars.hibernate.utils.HibernateConfig;
import org.hibernate.query.Query;


public class RentDao {

    private Transaction tx;
    private Rent rent;

    public void saveRent(Rent Rent) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            tx = session.beginTransaction();
            // save the Rent object
            session.save(Rent);
            // commit transaction
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public Rent selById(Long id) {
        tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Rent where id = :id");
            query.setParameter("id", id);

            rent = (Rent) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return rent;
    }


    //Aggiornamento prenotazione
    public void updateReservation(Date startDate, Date endDate, Long id) {
        tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Update Rent set startDate = :startDate, endDate = :endDate where id = :id");
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            query.executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    //Cancellazione prenotazione
    public void deleteReservation(Long id) {
        tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Delete from Rent where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    //Ritorna la lista di tutte le prenotazioni con i relativi utenti collegati
    public List <Rent> getRents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Rent", Rent.class).list();
        }
    }
}
