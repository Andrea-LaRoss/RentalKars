package com.rentalkars.hibernate.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.User;
import com.rentalkars.hibernate.utils.HibernateConfig;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

public class UserDao extends AbstractDao<User, Long>{

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

    public User selById(Long id) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            Query query = session.createQuery("from User where id = :id");
            query.setParameter("id", id);

            user = (User) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    //Controllo email durante registrazione utente
    public User selEmail(String email) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where email = :email");
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

    //Controllo utente durante login
    public User selEmailPassword(String email, String password) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("From User where email = :email and password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);

            user = (User) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }


    //Cancella utente
    public void deleteUser(Long id) {
        tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Delete from User u where u.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public List < User > getUsers() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public List < User > getUsers(String firstName) {
        tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where firstName =: firstName");
            query.setParameter("firstName", firstName);
            return query.getResultList();
        }
    }

    //Ritorna tutti gli utenti con l'ultima prenotazione effettuata
   /* public List <User> getUsersRents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("Select u.first_name, u.last_name, u.email, max(r.id) " +
                    "from User as u inner join rent as r where id = r.user_id").list();
       }
    }*/
}
