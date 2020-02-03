package dao;

import m2info.EntityManagerHelper;
import model.Velo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class VeloDAO implements DAO<Velo> {

    private Class<Velo> velo = model.Velo.class;

    @PersistenceContext
    EntityManager entityManager = EntityManagerHelper.getEntityManager();
    EntityManagerHelper helper;

    public VeloDAO(){ }

    @Override
    public Velo findById(long id){
        helper.beginTransaction();
        Velo searchedVelo = entityManager.find(velo, id);
        helper.commit();
        return searchedVelo;
    }

    public List<Velo> findAll(){
        return entityManager.createQuery("SELECT v FROM " + velo.getName() + " v").getResultList();
    }

    @Override
    public void create(Velo v){
        helper.beginTransaction();
        entityManager.persist(v);
        helper.commit();
    }

    @Override
    public void update(Velo v){
        helper.beginTransaction();
        entityManager.merge(v);
        helper.commit();
    }

    @Override
    public void delete(Velo v){
        helper.beginTransaction();
        entityManager.remove(v);
        helper.commit();
    }
}
