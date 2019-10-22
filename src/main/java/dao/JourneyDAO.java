package dao;

import m2info.EntityManagerHelper;
import model.Journey;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class JourneyDAO<Journey, P extends Serializable> implements DAO<Journey, P> {

    protected Class<Journey> journey;

    @PersistenceContext
    EntityManager entityManager;
    EntityManagerHelper helper;

    public JourneyDAO(){
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.journey = (Class<Journey>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public Journey findById(P id) {
        helper.beginTransaction();
        Journey searchedJourney = entityManager.find(journey, id);
        helper.commit();
        return searchedJourney;
    }

    @Override
    public List<Journey> findAll() {
        return entityManager.createQuery("SELECT * FROM " + journey.getName()).getResultList();
    }

    @Override
    public void create(Journey j) {
        helper.beginTransaction();
        entityManager.persist(j);
        helper.commit();
    }

    @Override
    public void update(Journey j) {
        helper.beginTransaction();
        entityManager.merge(j);
        helper.commit();
    }

    @Override
    public void delete(Journey j) {
        helper.beginTransaction();
        entityManager.remove(j);
        helper.commit();
    }
}
