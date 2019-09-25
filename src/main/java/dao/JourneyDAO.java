package dao;

import m2info.EntityManagerHelper;
import model.Journey;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JourneyDAO extends DAO<Journey> {


    private Class<Journey> journeyClass;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public JourneyDAO(){setClass(Journey.class);}

    public Journey findById(long id){
        return entityManager.find(journeyClass, id);
    }

    public List<Journey> findAll(){
        return entityManager.createQuery("SELECT * FROM " + journeyClass.getName()).getResultList();
    }

    public void save(Journey j){
        try{
            EntityManagerHelper.beginTransaction();
            entityManager.persist(j);
            EntityManagerHelper.commit();
        } catch(RuntimeException e){
            EntityManagerHelper.rollback();
            throw e;
        }
    }

    public void create(Journey j){
        helper.beginTransaction();
        entityManager.persist(j);
        helper.commit();
    }

    public Journey update(Journey j){
        helper.beginTransaction();
        Journey updatedParameter = entityManager.merge(j);
        helper.commit();
        return updatedParameter;
    }

    public void delete(Journey j){
        helper.beginTransaction();
        entityManager.remove(j);
        helper.commit();
    }
}
