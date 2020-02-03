package dao;

import m2info.EntityManagerHelper;
import model.Journey;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public class JourneyDAO implements DAO<Journey> {

    protected Class<Journey> journey = model.Journey.class;

    @PersistenceContext
    EntityManager entityManager = EntityManagerHelper.getEntityManager();
    EntityManagerHelper helper;

    public JourneyDAO(){ }

    @Override
    public Journey findById(long id) {
        helper.beginTransaction();
        Journey searchedJourney = entityManager.find(journey, id);
        helper.commit();
        return searchedJourney;
    }

    @Override
    public List<Journey> findAll() {
        return entityManager.createQuery("SELECT j FROM " + journey.getName() + " j").getResultList();
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
