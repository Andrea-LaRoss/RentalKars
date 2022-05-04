package com.rentalkars.hibernate.dao;

import java.util.List;


import com.rentalkars.hibernate.entity.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.utils.HibernateConfig;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CarDao {

    private Transaction tx;
    private Car car;

    public void saveCar(Car car) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.save(car);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    public void updateCar(Car car) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.merge(car);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public void removeCar(Car car){
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.remove(car);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public Car selById(Long id){
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            car = session.get(Car.class, id);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }


    public Car selByPlate(String numPlate) {
        tx = null;
        car = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Car> query = builder.createQuery(Car.class);
            Root<Car> carSet = query.from(Car.class);
            CriteriaQuery<Car> select = query.select(carSet);

            car = session.createQuery(select.where(builder.equal(carSet.get("numPlate"), numPlate))).getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }


    //Ritorna la lista di tutte le auto con l'ultima prenotazione effettuata su di esse
    public List <Car> getCars() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Car", Car.class).list();
        }
    }

}