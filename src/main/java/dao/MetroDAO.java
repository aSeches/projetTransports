package dao;

import m2info.EntityManagerHelper;
import model.Metro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class MetroDAO implements DAO<Metro> {

    private Class<Metro> metro = model.Metro.class;

    @PersistenceContext
    EntityManager entityManager = EntityManagerHelper.getEntityManager();
    EntityManagerHelper helper;

    public MetroDAO(){ }

    @Override
    public Metro findById(long id) {
        helper.beginTransaction();
        Metro searchedMetro = entityManager.find(metro, id);
        helper.commit();
        return searchedMetro;
    }

    @Override
    public List<Metro> findAll(){
        helper.beginTransaction();
        List<Metro> allMetros = entityManager.createQuery("SELECT m FROM " + metro.getName() + " m").getResultList();
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
