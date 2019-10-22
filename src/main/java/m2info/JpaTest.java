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
        JPATest.createBuses();
        JPATest.listBuses();
        JPATest.createMetros();
        JPATest.listMetros();
        JPATest.createVelos();
        JPATest.listVelos();

        tx.commit();
        manager.close();
        factory.close();
    }

    public void createCitizens(){
        int numOfCitizens = manager.createQuery("FROM Citizen c",Citizen.class).getResultList().size();
        if(numOfCitizens == 0){
            manager.persist(new Citizen("Yoann","Roazhon Park", "ISTIC", 15.00));
            manager.persist(new Citizen("Raoul","Le Bato", "ISTIC", 0.10));
            manager.persist(new Citizen("Jacques", "St-Augustin","Nauzan Plage", 10.00));
            manager.persist(new Citizen("Obi-Wan Kenobi","La Force", "Coruscant",40));
        }
    }

    public void listCitizens(){
        List<Citizen> resultList = manager.createQuery("FROM Citizen c",Citizen.class).getResultList();
        System.out.println("numOfCitizens = " + resultList.size());
        for (Citizen next : resultList){
            System.out.println("next citizen");
        }
    }

    public void createBuses(){
        int numOfBuses = manager.createQuery("FROM Bus b",Bus.class).getResultList().size();
        if(numOfBuses == 0){
            manager.persist(new Bus());
            manager.persist(new Bus());
            manager.persist(new Bus());
            manager.persist(new Bus());
        }
    }

    public void listBuses(){
        List<Bus> resultList = manager.createQuery("FROM Bus b",Bus.class).getResultList();
        System.out.println("numOfBuses = " + resultList.size());
        for (Bus next : resultList){
            System.out.println("next bus");
        }
    }

    public void createMetros(){
        int numOfMetros = manager.createQuery("FROM Metro m",Metro.class).getResultList().size();
        if(numOfMetros == 0){
            manager.persist(new Metro());
        }
    }

    public void listMetros(){
        List<Metro> resultList = manager.createQuery("FROM Metro m",Metro.class).getResultList();
        System.out.println("numOfBuses = " + resultList.size());
        for (Metro next : resultList){
            System.out.println("next metro");
        }
    }

    public void createVelos(){
        int numOfVelos = manager.createQuery("FROM Velo v",Velo.class).getResultList().size();
        if(numOfVelos == 0){
            manager.persist(new Velo());
            manager.persist(new Velo());
            manager.persist(new Velo());
            manager.persist(new Velo());
            manager.persist(new Velo());
        }
    }

    public void listVelos(){
        List<Velo> resultList = manager.createQuery("FROM Velo v",Velo.class).getResultList();
        System.out.println("numOfVelos = " + resultList.size());
        for (Velo next : resultList){
            System.out.println("next bicycle");
        }
    }

}

