package com.rentalkars.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.User;
import com.rentalkars.hibernate.utils.HibernateConfig;

import javax.persistence.criteria.*;

public class UserDao{

    private Transaction tx;
    private User user;


    public void saveUser(User user) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.save(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public void updateUser(User user) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.merge(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public void removeUser(User user){
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.remove(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public User selById(Long id){
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            user = session.get(User.class, id);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }


    public List<User> selByEmail(String email) {
        tx = null;
        List<User> users = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userSet = query.from(User.class);
            CriteriaQuery<User> select = query.select(userSet);

            users = session.createQuery(select.where(builder.like(userSet.get("email"), "%"+email+"%"))).getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }


    public List<User> selByFirstName(String firstName) {
        tx = null;
        List<User> users = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userSet = query.from(User.class);
            CriteriaQuery<User> select = query.select(userSet);

            users = session.createQuery(select.where(builder.like(userSet.get("firstName"), "%"+firstName+"%"))).getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }


    public List<User> selByLastName(String lastName) {
        tx = null;
        List<User> users = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userSet = query.from(User.class);
            CriteriaQuery<User> select = query.select(userSet);

            users = session.createQuery(select.where(builder.like(userSet.get("lastName"), "%"+lastName+"%"))).getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }


    public List <User> getUsers() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

}
