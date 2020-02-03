package dao;

import m2info.EntityManagerHelper;
import model.Citizen;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class CitizenDAO implements DAO<Citizen> {

    private Class<Citizen> citizenClass = model.Citizen.class;

    @PersistenceContext
    EntityManager entityManager = EntityManagerHelper.getEntityManager();
    EntityManagerHelper helper;

    public CitizenDAO(){ }

    @Override
    public Citizen findById(long id) {
        helper.beginTransaction();
        Citizen searchedCitizen = entityManager.find(citizenClass, id);
        helper.commit();
        System.out.println(searchedCitizen);
        return searchedCitizen;
    }

    @Override
    public List<Citizen> findAll(){
        return entityManager.createQuery("SELECT c FROM " + citizenClass.getName() + " c").getResultList();
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
