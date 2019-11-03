package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

@Entity
@Table(name="citizen")
public class Citizen implements Serializable {

    private long id;
    private String name, home, work;

    public Citizen(){};

    public Citizen(String name, String home, String work){
        this.name = name;
        this.home = home;
        this.work = work;
    }

    @Id
    @NotNull
    @GeneratedValue
    public long getId(){return id;}

    public void setId(long id){
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() { return home; }

    public void setHome(String home) {
        this.home = home;
    }

    public String getWork() { return work; }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public String toString(){
        return "Citizen [id=" + id + ", name=" + name + ", home=" + home + ", work=" + work;
    }

}
