package dao;

import m2info.EntityManagerHelper;
import model.Velo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class VeloDAO<Velo, P extends Serializable> implements DAO<Velo, P> {

    private Class<Velo> velo;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public VeloDAO(){
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.velo = (Class<Velo>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public Velo findById(P id){
        helper.beginTransaction();
        Velo searchedVelo = entityManager.find(velo, id);
        helper.commit();
        return searchedVelo;
    }

    public List<Velo> findAll(){
        return entityManager.createQuery("FROM " + velo.getName()).getResultList();
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
