package dao;

import m2info.EntityManagerHelper;
import model.Metro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class MetroDAO extends DAO<Metro> {

    private Class<Metro> metroClass;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public MetroDAO(){
        setClass(Metro.class);
    }

    public Metro findById(long id){
        return entityManager.find(metroClass, id);
    }

    public List<Metro> findAll(){
        return entityManager.createQuery("SELECT * FROM " + metroClass.getName()).getResultList();
    }

    public void create(Metro m){
        helper.beginTransaction();
        entityManager.persist(m);
        helper.commit();
    }

    public void save(Metro m){
        try{
            helper.beginTransaction();
            entityManager.persist(m);
            helper.commit();
        } catch (RuntimeException e){
            helper.rollback();
            throw e;
        }
    }

    public Metro update(Metro m){
        helper.beginTransaction();
        Metro updatedParameter = entityManager.merge(m);
        helper.commit();
        return updatedParameter;
    }

    public void delete(Metro m){
        helper.beginTransaction();
        entityManager.remove(m);
        helper.commit();
    }
}
