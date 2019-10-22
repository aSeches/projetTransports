package dao;

import m2info.EntityManagerHelper;
import model.Citizen;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class CitizenDAO<Citizen, P extends Serializable> implements DAO<Citizen, P> {

    private Class<Citizen> citizen;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public CitizenDAO(){
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.citizen = (Class<Citizen>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public Citizen findById(P id) {
        helper.beginTransaction();
        Citizen searchedCitizen = entityManager.find(citizen, id);
        helper.commit();
        return searchedCitizen;
    }

    @Override
    public List<Citizen> findAll(){
        return entityManager.createQuery("SELECT * FROM " + citizen.getName()).getResultList();
    }

    @Override
    public void create(Citizen c){
        helper.beginTransaction();
        entityManager.persist(c);
        helper.commit();
    }


    @Override
    public void update(Citizen c){
        helper.beginTransaction();
        entityManager.merge(c);
        helper.commit();
    }

    @Override
    public void delete(Citizen c){
        helper.beginTransaction();
        entityManager.remove(c);
        helper.commit();
    }

}
