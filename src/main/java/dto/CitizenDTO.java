package dto;

import java.io.Serializable;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

public class CitizenDTO implements Serializable {

    private long id;
    private String name, home, work;

   public CitizenDTO(){};

   public CitizenDTO(long id, String name, String home, String work){
        this.id = id;
        this.name = name;
        this.home = home;
        this.work = work;
    }

    public long getId() {
        return id;
    }

    public void setId(int id){
       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

}
