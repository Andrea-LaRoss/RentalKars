package com.rentalkars.hibernate.dao;

import com.rentalkars.hibernate.entity.User;
import com.sun.istack.internal.NotNull;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.List;

public class ClasseConCriterio {

}
                            //Classi di entità      //Chiave primaria
interface GenericRepository<I extends Serializable, E extends Serializable> {
    @NotNull
    List<I> SelTutti();

    void Inserisci(@NotNull I entity);
    void Aggiorna(@NotNull I entity);
    void Elimina(@NotNull I entity);
    void EliminaById(@NotNull E Id);
    I SelById(@NotNull E Id);
}

interface UserDaoInterface {
    User SelByEmail(String email);

    List<User> SelTutti();
    List<User> SelByNome(String nome);
    List<User> SelByCognome(String cognome);
    List<User> SelByDataNascita(LocalDate nascita);

    String SelLastPrenotazione();

    void Salva(User user);
    void Aggiorna(User user);
    void Elimina(User user);
}
    public User SelById(Long id) {
        return super.SelById(id);
    }

    public User SelByEmail(String email) {
        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> queryDefinition = queryBuilder.createQuery(User.class);

        Root<User> recordset = queryDefinition.from(User.class);
        queryDefinition.select(recordset).where(queryBuilder.equal(recordset.get("email"), email));

        User user = em.createQuery(queryDefinition).getSingleResult();
        em.clear();
        return user;
    }


    public List<User> SelByNome(String nome) {
        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> queryDefinition = queryBuilder.createQuery(User.class);

        String ToSearch = "%" + nome + "%";
        Root<User> recordset = queryDefinition.from(User.class);

        Expression<String> exp1 = queryBuilder.concat(recordset.<String>get("firstName"), " ");
        exp1 = queryBuilder.concat(exp1, recordset.<String>get("lastName"));

        Expression<String> exp2 = queryBuilder.concat(recordset.<String>get("lastName"), " ");
        exp2 = queryBuilder.concat(exp2, recordset.<String>get("firstName"));

        Predicate whereClause = queryBuilder.or(queryBuilder.like(exp1, ToSearch), queryBuilder.like(exp2, ToSearch));

        queryDefinition.select(recordset).where(whereClause);

        List<User> users = em.createQuery(queryDefinition).getResultList();
        em.clear();
        return users;
    }

    public List<User> SelTutti(){
        return super.SelTutti();
    }

    public void Salva(User user){
        super.Inserisci(user);
    }

    public void Aggiorna(User user){
        super.Aggiorna(user);
    }

    public void Elimina(User user){
        super.Elimina(user);
    }


