package dao;

import m2info.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public interface DAO<T> {

    T findById(long id);

    List<T> findAll();

    void create(T t);

    void update(T t);

    void delete(T t);

}
