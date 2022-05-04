package com.rentalkars.hibernate.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.rentalkars.hibernate.entity.Car;
import com.rentalkars.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.Rent;
import com.rentalkars.hibernate.utils.HibernateConfig;

import javax.persistence.criteria.*;


public class RentDao {

    private Transaction tx;
    private Rent rent;

    public void saveRent(Rent rent) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.save(rent);
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

    public List<Rent> listUserReservation(User user){
        tx = null;
        List<Rent> list = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Rent> query = builder.createQuery(Rent.class);
            Root<Rent> rentSet = query.from(Rent.class);
            CriteriaQuery<Rent> select = query.select(rentSet);

            list = session.createQuery(select.where(builder.equal(rentSet.get("user"), user))).getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }


    public List<Car> availableCars(LocalDate startDate, LocalDate endDate) {

        tx = null;
        List<Long> cars = new ArrayList<>();
        List<Car> available = new ArrayList<>();

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Rent> query = builder.createQuery(Rent.class);
            Root<Rent> rentSet = query.from(Rent.class);
            CriteriaQuery<Rent> select = query.select(rentSet);

            //Query che estrare i noleggi approvati avvenuti durante il periodo deciso
            Predicate between = builder.and(builder.or(builder.between(rentSet.get("startDate"), startDate, endDate),
                                            builder.between(rentSet.get("endDate"), startDate, endDate)),
                                            builder.equal(rentSet.get("status"), "Approvata"));

            List<Rent> reservations = session.createQuery(select.where(between).distinct(true)).getResultList();

            for(Rent tempReservations : reservations){
                cars.add(tempReservations.getCar().getId());
            }

            CriteriaQuery<Car> query2 = builder.createQuery(Car.class);
            Root<Car> carSet = query2.from(Car.class);
            CriteriaQuery<Car> selectFromCar = query2.select(carSet);

            if(cars.size() == 0) {

               available = session.createQuery(selectFromCar).getResultList();

            } else{

                Predicate notIn = builder.not(carSet.get("id").in(cars));
                available = session.createQuery(selectFromCar.where(notIn)).getResultList();
            }

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return available;
    }


    //Ritorna la lista di tutte le prenotazioni con i relativi utenti collegati
    public List <Rent> getRents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Rent",Rent.class).list();
        }
    }

}
