package com.rentalkars.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.Rent;
import com.rentalkars.hibernate.utils.HibernateConfig;
import org.hibernate.query.Query;


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

    /*
    * Query di selezione
     */

    //Aggiornamento prenotazione
    public void updateReservation(Date startDate, Date endDate, Long id) {
        Transaction tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query query = session.createQuery("Update rent set start_date = :startDate, end_date = :endDate where id = :id");
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
        Transaction tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query query = session.createQuery("Delete from rent where id = :id");
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
            return session.createQuery("select r.start_date, r.end_date, u.first_name, u.last_nasme " +
                    "from rent as r inner join user as u where u.id = r.user_id").list();
        }
    }
}
