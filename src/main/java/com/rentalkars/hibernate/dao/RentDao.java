package com.rentalkars.hibernate.dao;

import java.time.LocalDate;
import java.util.List;

import com.rentalkars.hibernate.entity.User;
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

            tx = session.beginTransaction();
            session.save(Rent);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    public void updateReservation(Rent rent) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.merge(rent);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    public void removeReservation(Rent rent){
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.remove(rent);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public Rent selById(Long id){
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            rent = session.get(Rent.class, id);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }

        return rent;
    }


    //Ritorna la lista di tutte le prenotazioni con i relativi utenti collegati
    public List <Rent> getRents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Rent", Rent.class).list();
        }
    }

}
