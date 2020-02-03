package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

@Entity
@Table(name="metro")
public class Metro implements Vehicle, Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "citizen")
    private ArrayList<Citizen> passengers;
    private int maxCapacity = 60;
    private double kmhSpeed = 70.00;

    public Metro(){
        passengers = new ArrayList<Citizen>();
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) { this.id = id; }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public double getSpeed() {
        return kmhSpeed;
    }

    @Override
    public void setSpeed(double speed) {
        this.kmhSpeed = speed;
    }

    @Override
    public ArrayList<Citizen> getPassengers(){
        return passengers;
    }

    @Override
    public void setPassengers(ArrayList<Citizen> passengers){
        this.passengers = passengers;
    }

    @Override
    public String toString(){
        return "Bus [id=" + id + ", passengers= " + getPassengers() + ", speed=" + getSpeed() + "]";
    }

    @Override
    public void getIn(Citizen c) {
        if(!(passengers.contains(c))){
            passengers.add(c);
        }
    }

    @Override
    public void getOut(Citizen c) {
        if((!(passengers.isEmpty())) && passengers.contains(c)){
            passengers.remove(c);
        }
    }

    @Override
    @Transient
    public boolean isFull() {
        if(passengers.size() >= maxCapacity){
            return true;
        } else {
            return false;
        }
    }

}
