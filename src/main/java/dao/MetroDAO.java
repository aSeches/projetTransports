package dao;

import m2info.EntityManagerHelper;
import model.Metro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class MetroDAO<Metro, P extends Serializable> implements DAO<Metro, P> {

    private Class<Metro> metro;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public MetroDAO(){
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.metro = (Class<Metro>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public Metro findById(P id) {
        helper.beginTransaction();
        Metro searchedMetro = entityManager.find(metro, id);
        helper.commit();
        return searchedMetro;
    }

    @Override
    public List<Metro> findAll(){
        helper.beginTransaction();
        List<Metro> allMetros = entityManager.createQuery("SELECT * FROM " + metro.getName()).getResultList();
        helper.commit();
        return allMetros;
    }

    @Override
    public void create(Metro m){
        helper.beginTransaction();
        entityManager.persist(m);
        helper.commit();
    }

    @Override
    public void update(Metro m){
        helper.beginTransaction();
        entityManager.merge(m);
        helper.commit();

    }

    @Override
    public void delete(Metro m){
        helper.beginTransaction();
        entityManager.remove(m);
        helper.commit();
    }
}
