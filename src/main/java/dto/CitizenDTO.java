package dto;

import javax.ws.rs.Path;
import java.io.Serializable;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

@Path("citizen")
public class CitizenDTO implements Serializable {

    private Long id;
    private String name, home, work;
    private double distance;

    public CitizenDTO(){};

    public CitizenDTO(String name, String home, String work, double distance){
        this.name = name;
        this.home = home;
        this.work = work;
        this.distance = distance;
    }

    public Long getId() {
        return id;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
