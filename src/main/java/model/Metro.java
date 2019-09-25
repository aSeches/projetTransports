package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */

@Entity
public class Metro implements Vehicle, Serializable {

    private long id;
    private ArrayList<Citizen> passengers;
    final private int maxCapacity = 60;
    final private double kmhSpeed = 70.00;

    public Metro(){
        passengers = new ArrayList<Citizen>();
    }

    @Override
    @Id
    @GeneratedValue
    public long getId() {
        return id;
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
    public double getSpeed() {
        return kmhSpeed;
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
    public boolean isFull() {
        if(passengers.size() >= maxCapacity){
            return true;
        } else {
            return false;
        }
    }

}
