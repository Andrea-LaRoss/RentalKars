package com.rentalkars.hibernate.dao;

import java.time.LocalDate;
import java.util.List;

import java.util.Date;

import com.rentalkars.hibernate.entity.Car;
import com.rentalkars.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.utils.HibernateConfig;
import org.hibernate.query.Query;

public class CarDao {

    private Transaction tx;
    private Car car;

    public void saveCar(Car car) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            tx = session.beginTransaction();
            // save the Car object
            session.save(car);
            // commit transaction
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public Car selById(Long id) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            Query query = session.createQuery("from Car where id = :id");
            query.setParameter("id", id);

            car = (Car) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }


    //Selezione con targa
    public Car selPlate(String numPlate) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Car where num_plate = :numPlate");
            query.setParameter("numPlate", numPlate);

            car = (Car) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }


    public void updateCar (String manufacturer, String model, String type, String numPlate, LocalDate regDate, Long id) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Update Car set manufacturer =: manufacturer, model =: model, type =: type, numPlate =: numPlate, regDate =: regDate where id =: id");
            query.setParameter("manufacturer", manufacturer);
            query.setParameter("model", model);
            query.setParameter("type", type);
            query.setParameter("numPlate", numPlate);
            query.setParameter("regDate", regDate);
            query.setParameter("id", id);
            query.executeUpdate();
        }
    }


    //Cancella modello
    public void deleteCar (Long id) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Car where id = :id");
            query.setParameter("id", id);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    //Ritorna la lista di tutte le auto con l'ultima prenotazione effettuata su di esse
    public List <Car> getCars() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Car", Car.class).list();
        }
    }

    public List <Car> getCars(String model) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Car where model =: model");
            query.setParameter("model", model);
            return query.getResultList();
        }
    }
}