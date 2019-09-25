package dao;

import m2info.EntityManagerHelper;
import model.Bus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class BusDAO extends DAO<Bus> {

    private Class<Bus> busClass;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public BusDAO(){
        setClass(Bus.class);
    }

    public Bus findById(long id){
        return entityManager.find(busClass, id);
    }

    public List<Bus> findAll(){
        return entityManager.createQuery("FROM " + busClass.getName()).getResultList();
    }

    public void create(Bus b){
        helper.beginTransaction();
        entityManager.persist(b);
        helper.commit();
    }

    public void save(Bus b){
        try{
            helper.beginTransaction();
            entityManager.persist(b);
            helper.commit();
        } catch (RuntimeException e){
            helper.rollback();
            throw e;
        }
    }

    public Bus update(Bus b){
        helper.beginTransaction();
        Bus updatedParameter = entityManager.merge(b);
        helper.commit();
        return updatedParameter;
    }

    public void delete(Bus b){
        helper.beginTransaction();
        entityManager.remove(b);
        helper.commit();
    }
}
