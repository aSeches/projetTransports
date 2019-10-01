package dao;

import m2info.EntityManagerHelper;
import model.Citizen;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class CitizenDAO extends DAO<Citizen> {

    private Class<Citizen> citizenClass;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public CitizenDAO(){
        setClass(Citizen.class);
    }

    public Citizen findById(long id){
        return entityManager.find(citizenClass, id);
    }

    public List<Citizen> findAll(){
        return entityManager.createQuery("SELECT * FROM " + citizenClass.getName()).getResultList();
    }

    public void create(Citizen c){
        helper.beginTransaction();
        entityManager.persist(c);
        helper.commit();
    }

    /*
    public void save(Citizen c){
        try{
            helper.beginTransaction();
            entityManager.persist(c);
            helper.commit();
        } catch (RuntimeException e){
            helper.rollback();
            throw e;
        }
    }
     */

    public Citizen update(Citizen c){
        helper.beginTransaction();
        Citizen updatedParameter = entityManager.merge(c);
        helper.commit();
        return updatedParameter;
    }

    public void delete(Citizen c){
        helper.beginTransaction();
        entityManager.remove(c);
        helper.commit();
    }

}
