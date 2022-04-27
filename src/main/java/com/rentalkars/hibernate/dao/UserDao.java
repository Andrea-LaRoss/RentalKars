package com.rentalkars.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalkars.hibernate.entity.User;
import com.rentalkars.hibernate.utils.HibernateConfig;
import org.hibernate.query.Query;

public class UserDao {

    public void saveUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            tx = session.beginTransaction();
            // save the User object
            session.save(user);
            // commit transaction
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    //Controllo email durante registrazione utente
    public User selEmail(String email) {
        Transaction tx = null;
        User user = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("Select * from User where email = :email");
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
        Transaction tx = null;
        User user = null;

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

    //Ricerca via nome
    public User selName(String firstName) {
        Transaction tx = null;
        User user = null;

        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query query = session.createQuery("Select * from User where first_name like :firstName %");
            query.setParameter("firstName", firstName);

            user = (User) query.uniqueResult();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }


    //Aggiorna email
    public void updateEmail(String email, Long id) {
        Transaction tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query query = session.createQuery("Update User set email = :email where id = :id");
            query.setParameter("email", email);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    //Aggiorna password
    public void updatePassword(String password, Long id) {
        Transaction tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query query = session.createQuery("Update User set password = :password where id = :id");
            query.setParameter("password", password);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    //Cancella utente
    public void deleteUser(Long id) {
        Transaction tx = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
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

    //Ritorna tutti gli utenti con l'ultima prenotazione effettuata
   /* public List <User> getUsersRents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("Select u.first_name, u.last_name, u.email, max(r.id) " +
                    "from User as u inner join rent as r where id = r.user_id").list();
       }
    }*/
}
