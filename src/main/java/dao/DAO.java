package dao;

import m2info.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public abstract class DAO<T extends Serializable> {

    private Class<T> dao;

   @PersistenceContext
   EntityManager entityManager;
   EntityManagerHelper helper;

   public final void setClass(Class<T> classToSet){
       this.dao = classToSet;
   }

   public T findById(long id){
       return entityManager.find(dao, id);
   }

   public List<T> findAll(){
       return entityManager.createQuery("SELECT * FROM " + dao.getName()).getResultList();
   }

   public void create(T entity){
       helper.beginTransaction();
       entityManager.persist(entity);
       helper.commit();
   }

   public void save(T entity){
       try{
           helper.beginTransaction();
           entityManager.persist(entity);
           helper.commit();
       } catch (RuntimeException e){
           helper.rollback();
           throw e;
       }
   }

   public T update(T entity){
       helper.beginTransaction();
       T updatedParameter = entityManager.merge(entity);
       helper.commit();
       return updatedParameter;
   }

   public void delete(T entity){
       helper.beginTransaction();
       entityManager.remove(entity);
       helper.commit();
   }

}
