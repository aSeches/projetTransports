package dao;

import com.mysql.fabric.xmlrpc.base.Param;
import m2info.EntityManagerHelper;
import model.Bus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class BusDAO<Bus, P extends Serializable> implements DAO<Bus,P> {

    protected Class<Bus> bus;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public BusDAO(){
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.bus = (Class<Bus>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public Bus findById(P id) {
        helper.beginTransaction();
        Bus searchedBus = entityManager.find(bus, id);
        helper.commit();
        return searchedBus;
    }

    @Override
    public List<Bus> findAll() {
        return entityManager.createQuery("SELECT * FROM " + bus.getName()).getResultList();
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
