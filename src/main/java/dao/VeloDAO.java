package dao;

import m2info.EntityManagerHelper;
import model.Velo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class VeloDAO extends DAO<Velo> {

    private Class<Velo> veloClass;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public VeloDAO(){
        setClass(Velo.class);
    }

    public Velo findById(long id){
        return entityManager.find(veloClass, id);
    }

    public List<Velo> findAll(){
        return entityManager.createQuery("FROM " + veloClass.getName()).getResultList();
    }

    public void create(Velo v){
        helper.beginTransaction();
        entityManager.persist(v);
        helper.commit();
    }

    public void save(Velo v){
        try{
            helper.beginTransaction();
            entityManager.persist(v);
            helper.commit();
        } catch (RuntimeException e){
            helper.rollback();
            throw e;
        }
    }

    public Velo update(Velo v){
        helper.beginTransaction();
        Velo updatedParameter = entityManager.merge(v);
        helper.commit();
        return updatedParameter;
    }

    public void delete(Velo v){
        helper.beginTransaction();
        entityManager.remove(v);
        helper.commit();
    }
}
