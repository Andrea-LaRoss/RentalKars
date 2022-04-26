package com.rentalkars.hibernate.dao;

import java.util.List;

import java.util.Date;

import com.rentalkars.hibernate.entity.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.utils.HibernateConfig;
import org.hibernate.query.Query;

public class CarDao {
    public void saveCar(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Car object
            session.save(car);
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

    //Selezione con produttore
    public Car selManufacturer(String manufacturer) {
        Transaction tx = null;
        Car car = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select * from car where manufacturer = :manufacturer");
            query.setParameter("manufacturer", manufacturer);

            car = (Car) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }

    //Selezione con modello
    public Car selModel(String model) {
        Transaction tx = null;
        Car car = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select * from car where model = :model");
            query.setParameter("model", model);

            car = (Car) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }

    //Selezione con tipo
    public Car selType (String type) {
        Transaction tx = null;
        Car car = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select * from car where type = :type");
            query.setParameter("type", type);

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
        Transaction tx = null;
        Car car = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select * from car where num_plate = :numPlate");
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

    //Selezione con data di immatricolazione
    public Car selRegDat(Date regDate) {
        Transaction tx = null;
        Car car = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select * from car where reg_date = :regDate");
            query.setParameter("regDate", regDate);

            car = (Car) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }

    //Cancella modello
    public void deleteCar (Long id) {
        Transaction tx = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from car where id = :id");
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
}