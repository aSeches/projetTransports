package m2info;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.*;

import java.util.List;

public class JpaTest {

    private EntityManager manager;

    public JpaTest(EntityManager manager){
        this.manager = manager;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        JpaTest JPATest = null;
        try {
            JPATest = new JpaTest(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPATest.createCitizens();
        JPATest.listCitizens();

        tx.commit();
        manager.close();
        factory.close();
    }

    public void createCitizens(){
        int numOfCitizens = manager.createQuery("Select a citizen from Citizens a",Citizen.class).getResultList().size();
        if(numOfCitizens == 0){
            Citizen citizen = new Citizen();
            manager.persist(citizen);
            manager.persist(new Citizen("Yoann","Roazhon Park", "ISTIC", 15.00));
            manager.persist(new Citizen("Raoul","Le Bato", "ISTIC", 0.10));
            manager.persist(new Citizen("Jacques", "St-Augustin","Nauzan Plage", 10.00));
            manager.persist(new Citizen("Obi-Wan Kenobi","La Force", "Coruscant",40));
        }
    }


    public void listCitizens(){
        List<Citizen> resultList = manager.createQuery("Select * from Citizen",Citizen.class).getResultList();
        System.out.println("numOfCitizens = " + resultList.size());
        for (Citizen next : resultList){
            System.out.println("next bus" + next);
        }
    }

}

