package dao;

import m2info.EntityManagerHelper;
import model.Bus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class BusDAO implements DAO<Bus> {

    protected Class<Bus> bus = model.Bus.class;

    @PersistenceContext
    EntityManager entityManager = EntityManagerHelper.getEntityManager();
    EntityManagerHelper helper;

    public BusDAO(){ }

    @Override
    public Bus findById(long id) {
        helper.beginTransaction();
        Bus searchedBus = entityManager.find(bus, id);
        helper.commit();
        return searchedBus;
    }

    @Override
    public List<Bus> findAll() {
        return entityManager.createQuery("SELECT b FROM " + bus.getName() + " b").getResultList();
    }

    @Override
    public void create(Bus b) {
        helper.beginTransaction();
        entityManager.persist(b);
        helper.commit();
    }

    @Override
    public void update(Bus b) {
        helper.beginTransaction();
        entityManager.merge(b);
        helper.commit();
    }

    @Override
    public void delete(Bus b) {
        helper.beginTransaction();
        entityManager.remove(b);
        helper.commit();
    }
}
